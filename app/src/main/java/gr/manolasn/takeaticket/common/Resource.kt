package gr.manolasn.takeaticket.common

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int? = null
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(message: String, statusCode: Int, data: T? = null) : Resource<T>(data, message, statusCode)
    class Loading<T> : Resource<T>()
}