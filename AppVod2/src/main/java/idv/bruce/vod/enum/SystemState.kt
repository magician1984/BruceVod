package idv.bruce.vod.enum

sealed class SystemState {

    object Preparing : SystemState()

    object Idle : SystemState()

    object Using : SystemState()

    object Cleaning : SystemState()

    class Error(val code: Int, val message: String) : SystemState()
}