defmodule MicroService.API.HelloEndpoint do
  import Plug.Conn
  def show(conn) do
    send_resp(conn, 200, "Ola seu tosco!")
  end
end