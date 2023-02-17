package se.widar.volvoweather.data.model.exceptions

class UnsuccessfulRequestException(message: String, val requestSubject: String) : RuntimeException(message)