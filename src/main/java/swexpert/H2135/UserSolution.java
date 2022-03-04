package swexpert.H2135;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

public class UserSolution {
    TaskMgr taskMgr = new TaskMgr();
    EventController eventController;
    ProcessorMgr processorMgr;

    void init(int mProcessors) {
        /*
         * 각 테스트 케이스의 처음에 호출된다.
         * 해당 테스트 케이스에서 초기 프로세서의 개수는 mProcessors 개이다.
         *
         * Parameters
         * mProcessors: 초기 프로세서 개수 ( 1 ≤ mProcessors ≤ 1,000 )
         */
        taskMgr = new TaskMgr();
        processorMgr = new ProcessorMgr(mProcessors, taskMgr);
        eventController = new EventController(taskMgr, processorMgr);
    }

    void newTask(int timestamp, char mTask[], int mOperations, int mMaxProes) {
    	/*
	    	timestamp 시점에 이름이 mTask 이고 남은 연산 횟수가 mOperations 배정 가능한  최대 프로세서의 개수가 mMaxProes인 작업이 요청된다.
	    	mTask는영문 소문자로 구성되어 있으며 ‘＼0’으로 끝난다. 길이는 10 이하이다. (‘＼0’ 제외)
	    	 이전에 이름이 mTask인 작업이 추가된 적이 없음이 보장된다.
	    	 해당 작업의 상태 변화는 위에서 언급한규칙에 따른다.

	    	Parameters
	    	timestamp: 해당 API 가 호출되는 시점 ( 0 ≤timestamp ≤ 10,000,000 )
	    	mTask: 요청되는작업의 이름 ( 1 ≤ | mTask |≤ 10 )
	    	mOperations: 남은 연산 횟수 ( 1 ≤ mOperations ≤2,000,000 )
	    	mMaxProes: 배정받을수 있는 최대 프로세서 개수 ( 1 ≤ mMaxProes ≤ 20)
    	 */
        eventController.simulateUntil(timestamp - 1);

        Task newTask = new Task(timestamp, mTask, mOperations, mMaxProes, charToString(mTask));
        taskMgr.readyTask.add(newTask);
        taskMgr.readyTaskHash.put(newTask.name, newTask);
        eventController.doneTimestamp.offer(newTask);

        // processor가 남아있는지 확인
        eventController.simulateUntil(timestamp);
    }

    void addProcessors(int timestamp, int mProcessors) {
    	/*
	    	timestamp 시점에 프로세서들이 mProcessors 개가 추가된다.
	    	추가된 프로세서들은 미배정 상태이며, 규칙에 따라 즉시 작업에 배정될 수 있다.

	    	Parameters
	    	timestamp: 해당 API 가 호출되는 시점 ( 0 ≤timestamp ≤ 10,000,000 )
	    	mProcessors: 추가되는 프로세서 개수 ( 1 ≤ mProcessors ≤ 100 )
    	 */

        // timestamp-1 시점까지 simulate
        eventController.simulateUntil(timestamp - 1);

        // processor 추가
        processorMgr.idleProcessorCnt += mProcessors;

        // processor 배정
        eventController.simulateUntil(timestamp);
    }



    int getTaskOperations(int timestamp, char mTask[]) {
        /*
        	timestamp 시점에 이름이 mTask 인 작업의 남은 연산 횟수를 반환한다.
    		이 시점에 완료 상태인 작업의 경우 남은 연산 횟수로 0을 반환한다.

    		mTask는 영문 소문자로 구성되어 있으며 ‘＼0’으로 끝나고 길이는 10 이하이다. (‘＼0’ 제외)
    		이전에 이름이 mTask인 작업이 요청 되었음이 보장된다.

    		Parameters

    		timestamp: 해당 API 가 호출되는 시점 ( 0 ≤timestamp ≤ 10,000,000 )
    		mTask: 확인할작업의 이름 ( 1 ≤ | mTask |≤ 10 )

    		Returns

    		이름이 mTask인 작업의 남은 연산 횟수
         */

        eventController.simulateUntil(timestamp);

        int remainOperation = -1;

        String name = charToString(mTask);
        Optional<Task> targetTask = Optional.empty();
        if (taskMgr.workingTask != null && taskMgr.workingTask.name.equals(name)){
            targetTask = Optional.ofNullable(taskMgr.workingTask);
        } else if (taskMgr.fullTask.size() > 0 && taskMgr.fullTask.containsKey(name)) {
            targetTask = Optional.ofNullable(taskMgr.fullTask.get(name));
        } else if (taskMgr.doneTask.size() > 0 && taskMgr.doneTask.containsKey(name)) {
            remainOperation = 0;
        } else if (taskMgr.readyTask.size() > 0 && taskMgr.readyTaskHash.containsKey(name)){
            targetTask = Optional.ofNullable(taskMgr.readyTaskHash.get(name));
        }

        if (targetTask.isPresent()) {
            remainOperation = targetTask.get().mOperations - targetTask.get().calculateDoneOper(timestamp);
        }

        return remainOperation;
    }

    public String charToString(char[] mTask) {
        String name = "";

        for (char eachChar : mTask){
            if (eachChar == '\0'){
                break;
            }
            name += String.valueOf(eachChar);
        }

        return name;
    }
}


class EventController {

    TaskMgr taskMgr;
    ProcessorMgr processorMgr;
    Integer simTimestamp = 0;

    Queue<Task> doneTimestamp = new PriorityQueue<Task>();

    public EventController(TaskMgr taskMgr, ProcessorMgr processorMgr) {
        this.taskMgr = taskMgr;
        this.processorMgr = processorMgr;
    }

    public void simulateUntil(int targetTimestamp) {
        if (targetTimestamp < 0)
            return;

        // Simulation 돌리기
        run(targetTimestamp);

        // processor가 남아있는지 확인
        if (processorMgr.idleProcessorCnt > 0) {
            // processor가 남아있으면 dispatch
            List<Task> needUpdateTask = processorMgr.dispatch(simTimestamp, processorMgr.idleProcessorCnt);
            if(needUpdateTask.size() > 0){
                needUpdateTask.forEach(task -> {
                    doneTimestamp.remove(task);
                    doneTimestamp.offer(task);
                });
            }
        }
    }

    public void run(int targetTimestamp) {
        int beforeSimTime = simTimestamp;

        // processor가 없으면 targetTimestamp 이전에 doneTimeStamp 있는지 확인
        while (doneTimestamp.size() > 0 && doneTimestamp.peek().expectedEndTime <= targetTimestamp) {
            // Move to next Done timestamp
            moveToNextDone();

            // processor가 남아있는지 확인
            if (processorMgr.idleProcessorCnt > 0) {
                // processor가 남아있으면 dispatch
                List<Task> needUpdateTask = processorMgr.dispatch(simTimestamp, processorMgr.idleProcessorCnt);
                if(needUpdateTask.size() > 0){
                    needUpdateTask.forEach(task -> {
                        doneTimestamp.remove(task);
                        doneTimestamp.offer(task);
                    });
                }
            }
        }

        if (beforeSimTime == simTimestamp)
            simTimestamp = targetTimestamp;
    }

    public void moveToNextDone() {
        Task doneTask = doneTimestamp.poll();
        simTimestamp = doneTask.expectedEndTime;

        taskMgr.terminate(doneTask.name);

        // processor 개수 늘리기
        processorMgr.idleProcessorCnt += doneTask.curProcessors;
    }
}

class ProcessorMgr {
    TaskMgr taskMgr;

    public Integer idleProcessorCnt;

    public ProcessorMgr(Integer idleProcessorCnt, TaskMgr taskMgr) {
        this.idleProcessorCnt = idleProcessorCnt;
        this.taskMgr = taskMgr;
    }

    public List<Task> dispatch(int timestamp, int idleProcessorCnt) {
        int addCnt = 0;
        int remainCnt = idleProcessorCnt;
        List<Task> needUpdateTask = new ArrayList<>();

        if (this.taskMgr.workingTask == null && taskMgr.readyTask.size() > 0) {
            this.taskMgr.workingTask = taskMgr.readyTask.poll();
            taskMgr.readyTaskHash.remove(this.taskMgr.workingTask.name);
        }

        while (isDispatchable(remainCnt)) {
            if (this.taskMgr.workingTask.getExpandableCnt() >= idleProcessorCnt) {
                addCnt = idleProcessorCnt;
                remainCnt = 0;
            } else {
                addCnt = this.taskMgr.workingTask.getExpandableCnt();
                remainCnt = idleProcessorCnt - addCnt;
            }
            this.idleProcessorCnt = remainCnt;
            idleProcessorCnt = remainCnt;

            if (this.taskMgr.workingTask.expectedEndTime != 999999999) {
                needUpdateTask.add(this.taskMgr.workingTask);
            }

            this.taskMgr.workingTask.addProcessor(timestamp, addCnt);

            if (this.taskMgr.workingTask.getExpandableCnt()==0) {
                this.taskMgr.fullTask.put(this.taskMgr.workingTask.name, this.taskMgr.workingTask);
                this.taskMgr.workingTask = null;
            }

            if (this.taskMgr.workingTask == null && taskMgr.readyTask.size() > 0) {
                this.taskMgr.workingTask = taskMgr.readyTask.poll();
                taskMgr.readyTaskHash.remove(this.taskMgr.workingTask.name);
            }
        }

        return needUpdateTask;
    }

    public boolean isDispatchable(int remainCnt) {
        return (remainCnt > 0 && this.taskMgr.workingTask != null && this.taskMgr.workingTask.getExpandableCnt() > 0);
    }
}

class Task implements Comparable<Task> {
    public String name;

    @Override
    public int compareTo(Task targetTask) {
        return this.expectedEndTime > targetTask.expectedEndTime ? 1 : -1;
    }

    public Integer createTimestamp;
    public char[] mTask;
    public Integer mOperations;
    public Integer mMaxProcessors;
    public String status;
    public Integer curProcessors = 0;
    public Queue<CurProcessorCnt> processorHist = new ConcurrentLinkedQueue<CurProcessorCnt>();
    public Integer expectedEndTime = 999999999;

    public Task(Integer createTimestamp, char[] mTask, Integer mOperations, Integer mMaxProcessors, String name) {
        this.createTimestamp = createTimestamp;
        this.mTask = mTask;
        this.mOperations = mOperations;
        this.mMaxProcessors = mMaxProcessors;

        this.name = name;
    }

    public int expectEndTime(int createTimestamp) {
        int remainOper = mOperations;
        int workingTime = 0;
        int processorHistCnt = processorHist.size();

        Queue<CurProcessorCnt> processorHistClone = new ConcurrentLinkedQueue<CurProcessorCnt>();
        processorHist.forEach(curProcessorCnt -> processorHistClone.add(curProcessorCnt));

        for (int i = 0; i < processorHistCnt; i++) {
            CurProcessorCnt curProcessorCnt = processorHistClone.poll();

            if (i != processorHistCnt - 1) {
                CurProcessorCnt nextProcessorCnt = processorHistClone.peek();

                remainOper -= (nextProcessorCnt.timestamp - curProcessorCnt.timestamp) * curProcessorCnt.processorCnt;
//                workingTime += nextProcessorCnt.timestamp - curProcessorCnt.timestamp;
            } else {
                workingTime += Math.ceil((double) remainOper / curProcessorCnt.processorCnt) + curProcessorCnt.timestamp;
            }
        }

//        return createTimestamp + workingTime;
        return workingTime;
    }

    public int calculateDoneOper(int timestamp) {
        int doneOper = 0;
        int processorHistCnt = processorHist.size();

        Queue<CurProcessorCnt> processorHistClone = new ConcurrentLinkedQueue<CurProcessorCnt>();
        processorHist.forEach(curProcessorCnt -> processorHistClone.add(curProcessorCnt));

        for (int i = 0; i < processorHistCnt; i++) {
            CurProcessorCnt curProcessorCnt = processorHistClone.poll();

            if (i != processorHistCnt-1) {
                CurProcessorCnt nextProcessorCnt = processorHistClone.peek();

                doneOper += (nextProcessorCnt.timestamp - curProcessorCnt.timestamp)*curProcessorCnt.processorCnt;
            } else {
                doneOper += (timestamp - curProcessorCnt.timestamp)*curProcessorCnt.processorCnt;
            }
        }

        return doneOper;
    }

    public Integer getExpandableCnt() {
        return mMaxProcessors - curProcessors;
    }

    public void addProcessor(Integer timestamp, int processorCnt) {
        curProcessors += processorCnt;
        processorHist.add(new CurProcessorCnt(timestamp, curProcessors));

        expectedEndTime = expectEndTime(createTimestamp);
    }


    static class CurProcessorCnt {
        public Integer timestamp;
        public Integer processorCnt;

        public CurProcessorCnt(Integer timestamp, Integer processorCnt) {
            this.timestamp = timestamp;
            this.processorCnt = processorCnt;
        }
    }
}

class TaskMgr {
    public Queue<Task> readyTask = new ConcurrentLinkedQueue<Task>();
    public Map<String, Task> readyTaskHash = new HashMap<String, Task>();
    public Map<String, Task> doneTask = new HashMap<String, Task>();
    public Map<String, Task> fullTask = new HashMap<String, Task>();
    public Task workingTask;

    public void terminate(String name) {
        Task endTask;
        if (fullTask.containsKey(name)) {
            endTask = fullTask.get(name);
            fullTask.remove(name);
        } else {
            endTask = workingTask;
            workingTask = null;
        }

        doneTask.put(endTask.name, endTask);
    }
}