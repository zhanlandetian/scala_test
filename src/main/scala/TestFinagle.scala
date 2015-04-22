/**
 * Created by wangjunfei on 4/22/15.
 */
import com.twitter.finagle.http.{RequestProxy,Request,Response}
import data.UserData
import com.twitter.finagle.{Service,Filter}
import com.twitter.util.Future
import org.jboss.netty.handler.codec.http.HttpHeaders.Names
import org.jboss.netty.handler.codec.http.HttpResponseStatus

case class AuthorizedRequest(request:Request,userData: UserData) extends RequestProxy

trait LoginService{
  def login(username:String,password:String):Boolean
}
class AlwaysValidLoginService extends LoginService{
  override def login(username: String, password: String): Boolean = true
}
//class AuthentcationFilter(loginService:LoginSer)