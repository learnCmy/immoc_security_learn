package com.imooc.mapper;

import tk.mybatis.mapper.genid.GenId;

public class IdWorkerGenId implements GenId<Long> {


    private static long workerId;
    private static long dataCenterId;
    private static long sequence = 0L;

    private static long twepoch = 1288834974657L;

    private static long workerIdBits = 5L;
    private static long dataCenterIdBits = 5L;
    private static long maxWorkerId = -1L ^ (-1L << workerIdBits);
    private static long maxDataCenterId = -1L ^ (-1L << dataCenterIdBits);
    private static long sequenceBits = 12L;

    private static long workerIdShift = sequenceBits;
    private static long dataCenterIdShift = sequenceBits + workerIdBits;
    private static long timestampLeftShift = sequenceBits + workerIdBits + dataCenterIdBits;
    private static long sequenceMask = -1L ^ (-1L << sequenceBits);

    private static long lastTimestamp = -1L;


    /**
     * 生成器使用
     *
     * @param table
     * @param column
     * @return
     */
    @Override
    public synchronized Long genId(String table, String column) {

        return nextId();
    }


    /**
     * 自生成
     *
     * @return
     */
    public synchronized static Long nextId() {
        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            System.out.println(String.format("clock is moving backwards.  Rejecting requests until %d.", lastTimestamp));
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }

        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            if (sequence == 0) {
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0L;
        }

        lastTimestamp = timestamp;

        return ((timestamp - twepoch) << timestampLeftShift) | (dataCenterId << dataCenterIdShift) | (workerId << workerIdShift) | sequence;
    }


    protected static long timeGen() {
        return System.currentTimeMillis();
    }


    protected static long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }


    public IdWorkerGenId(long workerId, long dataCenterId) {
        // sanity check for workerId
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (dataCenterId > maxDataCenterId || dataCenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDataCenterId));
        }
        this.workerId = workerId;
        this.dataCenterId = dataCenterId;
        System.out.println(String.format("worker starting. timestamp left shift %d, datacenter id bits %d, worker id bits %d, sequence bits %d, workerid %d", timestampLeftShift, dataCenterIdBits, workerIdBits, sequenceBits, workerId));
    }

    public IdWorkerGenId() {
    }
}
