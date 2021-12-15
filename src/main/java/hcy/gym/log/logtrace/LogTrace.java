package hcy.gym.log.logtrace;

import hcy.gym.log.TraceStatus;

public interface LogTrace {

    TraceStatus begin(String message);
    void end(TraceStatus status);
    void exception(TraceStatus status, Exception e);

}
