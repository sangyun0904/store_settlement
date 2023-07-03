package com.example.storesettlement.utils;

import com.example.storesettlement.dto.AuthenticationResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class DefaultResponse<T> {

    @Schema()
    private int statusCode;
    private String responseMessage;
    @Schema(anyOf = {AuthenticationResponse.class})
    private T data;

    public DefaultResponse(final int statusCode, final String responseMessage) {
        this.statusCode = statusCode;
        this.responseMessage = responseMessage;
        this.data = null;
    }

    public static<T> DefaultResponse<T> res(final int statusCode, final String responseMessage) {
        return res(statusCode, responseMessage, null);
    }

    public static<T> DefaultResponse<T> res(final int statusCode, final String responseMessage, final T t) {
        return DefaultResponse.<T>builder()
                .data(t)
                .statusCode(statusCode)
                .responseMessage(responseMessage)
                .build();
    }

}
