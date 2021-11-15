package lesson28;

import org.slf4j.MDC;

/*
 * @author: cm
 * @date: Created in 2021/11/15 18:15
 * @description:
 */
public class TraceRunnable implements Runnable {
    private String tranceId;
    private Runnable target;

    public TraceRunnable(Runnable target) {
        this.tranceId = TraceUtil.get();
        this.target = target;
    }

    @Override
    public void run() {
        try {
            TraceUtil.set(this.tranceId);
            MDC.put(TraceUtil.MDC_TRACE_ID, TraceUtil.get());
            this.target.run();
        } finally {
            MDC.remove(TraceUtil.MDC_TRACE_ID);
            TraceUtil.remove();
        }
    }

    public static Runnable trace(Runnable target) {
        return new TraceRunnable(target);
    }
}
