package com.hoangtn.techbank.account.command.api.payload;

import com.hoangtn.techbank.account.common.dto.BaseResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class OpenAccountResponse extends BaseResponse {
    private String id;

    public OpenAccountResponse(String id, String message) {
        super(message);
        this.id = id;
    }
}
