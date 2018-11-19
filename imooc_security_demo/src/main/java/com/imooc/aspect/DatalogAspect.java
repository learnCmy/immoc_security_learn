package com.imooc.aspect;

import com.imooc.mapper.ActionMapper;
import com.imooc.pojo.Action;
import com.imooc.dto.ActionTypeEnum;
import com.imooc.dto.ChangeItem;
import com.imooc.utils.DiffUtil;
import com.imooc.utils.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.beanutils.PropertyUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * @Auther: cmy
 * @Date: 2018/11/16 16:01
 * @Description:
 */
@Aspect
@Component
@Slf4j
public class DatalogAspect {

    @Autowired
    private ActionMapper actionMapper;

    @Pointcut("execution(* com.imooc.mapper.CatMapper.insert*(..))")
    public void save() {
    }

    @Pointcut("execution(* com.imooc.mapper.CatMapper.update*(..))")
    public void update() {
    }

    @Pointcut("execution(* com.imooc.mapper.CatMapper.delete*(..))")
    public void delete() {
    }

    @Around("save()||update()||delete()")
    public Object addOperateLog(ProceedingJoinPoint pjp) throws Throwable {
        Object returnObj = null;
        //before
        String method = pjp.getSignature().getName();
        System.out.println("method:"+method);
        ActionTypeEnum actionTypeEnum = null;
        Action action = new Action();
        Long id = null;
        Object oldObj = null;
        try {
            Object obj = pjp.getArgs()[0];
            //判断是什么类型的操作,增加\删除\还是更新
            if (method.startsWith("insert")) {
                //新增操作,before直接获取,after记录下新增之后的id
                actionTypeEnum = ActionTypeEnum.INSERT;
                List<ChangeItem> insertChangeItems = DiffUtil.getInsertChangeItems(obj);
                action.setChangeItem(JsonUtils.objectToJson(insertChangeItems));
                action.setObjectClass(obj.getClass().getName());
            } else if (method.startsWith("update")) {
                //更新操作,before获取操作之前的记录,after获取操作之后的记录,然后diff
                actionTypeEnum = ActionTypeEnum.UPDATE;
                id=Long.valueOf( PropertyUtils.getProperty(obj, "id").toString());
                if (id!=null){
                    //得到原来的对象
                    oldObj=DiffUtil.getObjectById(pjp.getTarget(),id);
                    action.setObjectClass(oldObj.getClass().getName());
                    action.setObjectId(id);
                }
            }else if (method.startsWith("delete")){
                //删除操作,before根据id取记录
                id = Long.valueOf(PropertyUtils.getProperty(pjp.getArgs()[0], "id").toString());
                actionTypeEnum = ActionTypeEnum.DELETE;
                oldObj=DiffUtil.getObjectById(pjp.getTarget(),id);
                ChangeItem changeItem = DiffUtil.getDeleteChangeItem(oldObj);
                action.setChangeItem(JsonUtils.objectToJson(changeItem));
                action.setObjectClass(obj.getClass().getName());
                action.setObjectId(id);
            }

            returnObj = pjp.proceed(pjp.getArgs());
            //after
            action.setActionType(actionTypeEnum.getCode());
            if (ActionTypeEnum.INSERT==actionTypeEnum){
                Object newId = PropertyUtils.getProperty(pjp.getArgs()[0], "id");
                action.setObjectId(Long.valueOf(newId.toString()));
            }else if (ActionTypeEnum.UPDATE==actionTypeEnum){
                Object newObj = DiffUtil.getObjectById(pjp.getTarget(), id);
                List<ChangeItem> changeItems = DiffUtil.getChangeItems(oldObj, newObj);
                action.setChangeItem(JsonUtils.objectToJson(changeItems));
            }
            action.setOperator("admin");//TODO 操作者
            action.setOperateTime(new Date());
            // 3保存操作记录
            actionMapper.insert(action);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e.getMessage(),e);
        }
        return returnObj;
    }


}
