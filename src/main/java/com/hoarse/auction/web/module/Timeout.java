package com.hoarse.auction.web.module;

import java.util.concurrent.*;

public class Timeout

{
    public static String runWithTimeout(Callable<String>task, long timeout, TimeUnit unit){
        ExecutorService executor = Executors.newSingleThreadExecutor();
        Future<String> future = executor.submit(task);

        try {
            // 타임아웃 설정
            String result = future.get(timeout, unit);
            if (future.isDone()) {
                // 작업이 타임아웃 이전에 완료되었을 경우
                System.out.println("작업이 정상적으로 완료되었습니다.");
            }
            return result;
        } catch (TimeoutException e) {
            // 타임아웃 예외 발생 시 실행
            System.out.println("타임아웃 발생");
            return "타임아웃이 발생했습니다.";
        } catch (InterruptedException | ExecutionException e) {
            // 다른 예외 발생 시 실행
            return "작업 실행 중 오류가 발생했습니다.";
        } finally {
            executor.shutdown();
        }
    }
}
