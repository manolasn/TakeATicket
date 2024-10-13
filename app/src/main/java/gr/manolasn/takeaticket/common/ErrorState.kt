package gr.manolasn.takeaticket.common

data class ErrorState(
    val errorMessage: String = String(),
    val errorCode: Int = -1
)