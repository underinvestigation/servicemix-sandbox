/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.servicemix.nmr.management.stats;

/**
 * A time statistic implementation
 *
 * @version $Revision: 482795 $
 */
public class TimeStatistic extends Statistic {
    private long maxTime;
    private long minTime;
    private long totalTime;
    private TimeStatistic parent;

    public TimeStatistic(String name, String description) {
        this(name, "millis", description);
    }

    public TimeStatistic(TimeStatistic parent, String name, String description) {
        this(name, description);
        this.parent = parent;
    }

    public TimeStatistic(String name, String unit, String description) {
        super(name, unit, description);
    }

    public synchronized void reset() {
        super.reset();
        maxTime = 0;
        minTime = 0;
        totalTime = 0;
    }

    public synchronized void updateValue(long time) {
        updateSampleTime();
        updateUpdateCount();
        totalTime += time;
        if (time > maxTime) {
            maxTime = time;
        }
        if (time < minTime || minTime == 0) {
            minTime = time;
        }
        if (parent != null) {
            parent.updateValue(time);
        }
    }

    public synchronized void increment() {
        long time = getLastSampleTime();
        updateSampleTime();
        updateUpdateCount();
        time = getLastSampleTime() - time;
        totalTime += time;
        if (time > maxTime) {
            maxTime = time;
        }
        if (time < minTime || minTime == 0) {
            minTime = time;
        }
        if (parent != null) {
            parent.updateValue(time);
        }
    }

    /**
     * @return the maximum time of any step
     */
    public long getMaxTime() {
        return maxTime;
    }

    /**
     * @return the minimum time of any step
     */
    public synchronized long getMinTime() {
        return minTime;
    }

    /**
     * @return the total time of all the steps added together
     */
    public synchronized long getValue() {
        return totalTime;
    }

    /**
     * @return the average time calculated by dividing the
     *         total time by the number of counts
     */
    public synchronized double getAverageTime() {
        if (updateCount.get() == 0) {
            return 0;
        }
        double d = totalTime;
        return d / updateCount.get();
    }


    /**
     * @return the average time calculated by dividing the
     *         total time by the number of counts but excluding the
     *         minimum and maximum times.
     */
    public synchronized double getAverageTimeExcludingMinMax() {
        if (updateCount.get() <= 2) {
            return 0;
        }
        double d = totalTime - minTime - maxTime;
        return d / (updateCount.incrementAndGet() - 2);
    }


    /**
     * @return the average number of steps per second
     */
    public double getAveragePerSecond() {
        double d = 1000;
        double averageTime = getAverageTime();
        if (averageTime == 0) {
            return 0;
        }
        return d / averageTime;
    }

    /**
     * @return the average number of steps per second excluding the min & max values
     */
    public double getAveragePerSecondExcludingMinMax() {
        double d = 1000;
        double average = getAverageTimeExcludingMinMax();
        if (average == 0) {
            return 0;
        }
        return d / average;
    }

    public TimeStatistic getParent() {
        return parent;
    }

    public void setParent(TimeStatistic parent) {
        this.parent = parent;
    }

    protected synchronized void appendFieldDescription(StringBuffer buffer) {
        buffer.append(" update count: ");
        buffer.append(Long.toString(updateCount.get()));
        buffer.append(" maxTime: ");
        buffer.append(Long.toString(maxTime));
        buffer.append(" minTime: ");
        buffer.append(Long.toString(minTime));
        buffer.append(" totalTime: ");
        buffer.append(Long.toString(totalTime));
        buffer.append(" averageTime: ");
        buffer.append(Double.toString(getAverageTime()));
        buffer.append(" averageTimeExMinMax: ");
        buffer.append(Double.toString(getAverageTimeExcludingMinMax()));
        buffer.append(" averagePerSecond: ");
        buffer.append(Double.toString(getAveragePerSecond()));
        buffer.append(" averagePerSecondExMinMax: ");
        buffer.append(Double.toString(getAveragePerSecondExcludingMinMax()));
        super.appendFieldDescription(buffer);
    }

}
