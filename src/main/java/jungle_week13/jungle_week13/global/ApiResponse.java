package jungle_week13.jungle_week13.global;

public record ApiResponse<T> (
        T data,
        String message,
        String code
){

    public static <T> ApiResponse<T> ofSuccess(T data) {
        return new ApiResponse<>(data, "성공", "S001");
    }
    public static <T> ApiResponse<T> ofSuccess() {
        return new ApiResponse<>(null, "성공", "S001");
    }
    public static <T> ApiResponse<T> ofFail(T data) {
        return new ApiResponse<>(data, "실패", "S002");
    }
    public static <T> ApiResponse<T> ofFail() {
        return new ApiResponse<>(null, "실패", "S002");
    }
}