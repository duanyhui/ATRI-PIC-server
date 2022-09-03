package duan.atripic.exception;

/**
 * @Description: 持久化异常
 * @Author: duanyhui
 * @Date: 2022/9/3
 */

public class RedisException extends RuntimeException {
	public RedisException() {
	}

	public RedisException(String message) {
		super(message);
	}

	public RedisException(String message, Throwable cause) {
		super(message, cause);
	}
}
