package xyz.luchengeng.extracurriculum.management.exception

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such entity")
class NotFoundException : Exception()

@ResponseStatus(value= HttpStatus.UNAUTHORIZED, reason="Operation forbidden")
class NotAuthorizedException : Exception()