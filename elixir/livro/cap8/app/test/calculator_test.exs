defmodule App.CalculatorTest do
    # async deve ser usado com cuidado
    use ExUnit.Case, async: true

    setup do
        {:ok, hamilton: 2, mari: 3}
    end


    @moduletag :hamilton
    test "should return 4 when multiply 2 by 2", %{hamilton: valor} do 
        assert App.Calculator.multiply(2, valor) == 4
    end

    @moduletag :mari
    test "should return 6 when multiply 2 by 3", %{mari: valor} do 
        assert App.Calculator.multiply(2, valor) == 6
    end

    test "should return nil when multiply 2 by nil" do
        assert App.Calculator.multiply(2, nil) == nil
    end

    test "should return nil when multiply nil by 2" do
        assert App.Calculator.multiply(nil, 2) == nil
    end

    @moduletag :negativo
    test "shouldn't return 10 when multiply 2 by 4" do
        refute App.Calculator.multiply(2, 4) == 10
    end
   
end