package duan.atripic.exception;

/**
 * @Description: 404异常
 * @Author: duanyhui
 * @Date: 2022/9/3
 */

public class NotFoundException extends RuntimeException {
	public NotFoundException() {
	}

	public NotFoundException(String message) {
		super(message);
	}

	public NotFoundException(String message, Throwable cause) {
		super(message, cause);
	}
}
