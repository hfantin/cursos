# estruturas de controle
defmodule Fluxo do
    def testeIfElse(x \\nil) do 
        if x do 
            "x é valido" 
        else 
            "x é nulo"
        end
    end

    def testeIf(x \\nil) do 
        if x == 10 do 
            "x é 10" 
        end
    end

    def testeIfUmaLinha(x \\nil) do 
        if x == 10 do "x é 10" end
    end

    # unless - contrario do if
    def testeUnless(x \\nil) do 
        unless x == 10 do 
            "x nao é 10" 
        else 
            "x é 10"
        end
    end
    

    def testeCond(x \\nil) do
        cond do
            x == 10 -> "X é 10"
            x == 11 -> "X é 11"
            x == 12 -> "X é 12"
        end
    end

    def testeCase(x \\nil) do
        case x do
            :mari -> "é a mari"
            :samuca -> "é o samuca"
            :hamilton-> "é o hamilton"
            _ -> "não conheco"
        end
    end

end