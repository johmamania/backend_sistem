package com.backend_sistem.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record MsgResp(@JsonProperty(value = "message") String message){

}
