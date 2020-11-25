import java.io.BufferedReader
import java.io.File
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.ServerSocket
import java.net.Socket
import java.net.SocketAddress

fun main(args:Array<String>)
{
    val SocketServer=thread()
    SocketServer.start()

}
class thread:Thread()
{
    override fun run()
    {
        val socket = ServerSocket(5005)
        var count=0
        println("Server Start!")
        while(true)
        {
            val SocketClient=socket.accept()
            print("已連線")
            Thread{

                val input = SocketClient?.getInputStream()
                val reader = BufferedReader(InputStreamReader(input))
                val output=SocketClient.getOutputStream()
                var writer=PrintWriter(output,true)
                var data=reader.readLine()
                if (data==null)
                {

                }
                else
                {
                    data=data.toUpperCase()
                    var file=File("data.txt")
                    if(file.readText()=="")
                    {
                        file.appendText(data)
                    }
                    else
                    {
                        file.appendText(" "+data)
                    }

                    var array=file.readText().split(" ")
                    var YourNumber=array.size
                    writer.println(YourNumber)
                    while(YourNumber!=1)
                    {
                        Thread.sleep(500)
                        var dequeue=file.readText().split(" ")
                        for(x in 0..dequeue.size-1)
                        {
                            if(dequeue[x]==data)
                            {
                                YourNumber=x+1
                                writer.println(YourNumber)
                                break
                            }
                        }
                    }
                }

            }.start()

        }

    }
}