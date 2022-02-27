package jpabook.jpashop.global.error;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;

@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum ErrorCode {

    // Common
    INVALID_INPUT_VALUE(400, "C001", "올바르지 않은 입력값입니다."),
    METHOD_NOT_ALLOWED(405, "C002", "지원하지 않는 HTTP 메소드입니다."),
    ENTITY_NOT_FOUND(404, "C003", "엔티티를 찾을 수 없습니다."),
    INTERNAL_SERVER_ERROR(500, "C004", "Server Error"),
    INVALID_TYPE_VALUE(400, "C005", "올바르지 않은 타입입니다."),
    HANDLE_ACCESS_DENIED(403, "C006", "접근이 거부되었습니다."),
    UNSUPPORTED_MEDIA_TYPE(415, "C007", "지원하지 않는 미디어 타입입니다."),


    // Member
    MEMBER_NOT_FOUND(404, "M001", "회원을 찾을 수 없습니다"),
    EMAIL_DUPLICATION(409, "M003", "이미 가입된 회원입니다."),
    LOGIN_INPUT_INVALID(400, "M004", "입력값이 잘못되었습니다."),
    PASSWORD_FAILED_EXCEEDED(401, "M005", "비밀번호 실패 횟수를 초과했습니다."),

    // Auth
    INVALID_AUTH_TOKEN(400, "A001", "인증이 되지 않은 사용자입니다.");

    private final String code;
    private final String message;
    private final int status;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

}