package com.imooc.pojo;

import com.fasterxml.jackson.annotation.JsonView;
import com.imooc.aspect.Datalog;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Column;
import javax.persistence.Id;
@ToString
public class Cat {
    public interface CatSimpleView{};
    public  interface  CatDetailView extends CatSimpleView {};

    @Id
    //@KeySql(genId = MyGenId.class)
    private Integer id;

    @Column(name = "cat_age")
    @Datalog(name="猫年龄")
    private Integer catAge;

    @Column(name = "cat_name")
    @NotEmpty
    @Datalog(name="猫的名称")
    private String catName;





    /**
     * @return id
     */
    @JsonView(CatSimpleView.class)
    public Integer getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * @return cat_age
     */
    @JsonView(CatDetailView.class)
    public Integer getCatAge() {
        return catAge;
    }

    /**
     * @param catAge
     */
    public void setCatAge(Integer catAge) {
        this.catAge = catAge;
    }

    /**
     * @return cat_name
     */
    public String getCatName() {
        return catName;
    }

    /**
     * @param catName
     */
    public void setCatName(String catName) {
        this.catName = catName;
    }
}