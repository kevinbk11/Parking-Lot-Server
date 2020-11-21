import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.net.ServerSocket
import java.net.Socket

fun main(args:Array<String>)
{
    val SocketServer=thread()
    SocketServer.start()

}
class thread:Thread()
{
    override fun run()
    {
        val socket = ServerSocket(2333)
        var count=0
        while(true)
        {
            val SocketClient=socket.accept()
            count++
            println("新的用戶已連線!    目前有${count}位客人")
            Thread{
                while(true)
                {
                    val input = SocketClient?.getInputStream()
                    val reader = BufferedReader(InputStreamReader(input))
                    val data=reader.readLine()
                    if (data==null)
                    {
                        count--
                        println("有客戶斷開連線!    目前有${count}位客人")
                        break
                    }
                    else
                    {
                        println(data)
                        File("data.txt").writeText(data)
                    }
                }

            }.start()

        }

    }
}