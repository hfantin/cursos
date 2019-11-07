defmodule MicroService.Router do
  use Plug.Router
  require Logger
  alias MicroService.API
  
  plug :match
  plug Plug.Parsers,
    parsers: [:json],
    pass: ["application/json"],
    json_decoder: Poison
  plug :dispatch
  
  get "/hello" do
    Logger.info "acessando hello"
    API.HelloEndpoint.show(conn)
  end
  
  match _ do
    send_resp(conn, 404, "pagina não existe")
  end
end