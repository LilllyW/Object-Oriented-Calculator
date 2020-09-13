package com.example.calculator

/**
 * 每個計算機按鈕皆會產生此一物件
 */
abstract class Output(val number: String) {
    abstract fun output(preOutput: Output): String //all objects (0,1,*,+ etc) be clicked call this function to show full history or result on textView 畫面輸出
    /**
     * 每個按鈕物件按下去會呼叫此方法，各自呈現當前的輸出畫面
     */
    abstract fun numbersGetter(): Double //numbers 輸出, get (leftNumber) which is before operator & combine formulaList to Output for show history
    /**
     * 數字集合，用來取得前一按鈕Output物件的數值
     */
    abstract fun operatorListGetter(): List<Operator>// operatorList 輸出,  to evaluate the result
    /**
     * 整串數字運算符的集合
     */
    abstract fun operatorNumbersGetter(): Double
    /**
     * 取得 operatorList 運算物件的左值 , 為作取代 (when click "123 + -" 運算子物件將從 "123+" Addition(123) => 變為 "123-" Subtraction(123) )需求而增加
     */
    abstract fun operatorGetter(): Operator //use operatorListGetter 即可取得值
    /**
     * 用於取得前一物件 preOutput的 operator 的方法
     */
    abstract fun getOperators(nowOutput: Output): List<Operator>
    /**
     * 同[getOperators]說明, 新增運算物件的方法, 當 123+ 按到 "+"時 請 preOutput前一物件 "3" 呼叫此方法把運算物件 Addition(123)放進去
     */
    abstract fun getOperators(): List<Operator> // for number class use
    /**
     * 同[getOperators]說明, 特別專屬於前一物件與當前物件為 Number class時使用
     */
}

class Number(number: String): Output(number){ // 6
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {//不同按鈕作不同output //result_textView.text = Number.output()
        var inputFormula = ""

        numbers = preOutput.numbersGetter() * 10 + this.number.toDouble() // 45 * 10 + 6
        val list = preOutput.getOperators()
        operatorList.addAll(list)

        operatorList.forEach { operator ->
            inputFormula += operator.formula()
        }

        inputFormula += numbers.toString()

        return inputFormula
    }

    override fun getOperators(): List<Operator> {
        return operatorListGetter().toList()
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // no use & empty
        return operatorList
    }

    override fun operatorNumbersGetter(): Double { // no use
        return numbers
    }

    override fun operatorGetter(): Operator { // no use
        return operators
    }

}

class EqualToEvaluate(symbol: String): Output(symbol){
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        numbers = preOutput.numbersGetter()
        operators = Equal(numbers)

        val equalOperator = Equal(numbers) // Equal(78)
        val resultOperator = (preOutput.operatorListGetter() + equalOperator).reduce { leftOperator: Operator, rightOperator: Operator -> leftOperator.evaluate(rightOperator) }

        operatorList.clear()
        numbers = resultOperator.left

        return resultOperator.left.toString() // 657
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun getOperators(): List<Operator> { // only number class use
        return operatorList
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // empty
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return numbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }
}

class Add(symbol: String): Output(symbol) {
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private var operatorNumbers = 0.0
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        var inputFormula = ""
        numbers = preOutput.numbersGetter()

        operatorNumbers = preOutput.operatorNumbersGetter()
        operators = Addition(operatorNumbers)

        val list = preOutput.getOperators(this)
        operatorList.addAll(list)

        operatorList.forEach { operator ->
            inputFormula += operator.formula()
        }
        numbers = 0.0

        return inputFormula // 123 + 456 +
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.removeAt(list.size - 1)
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun getOperators(): List<Operator> { // only number class use
        return operatorListGetter().toList()
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // Addition(123), Addition(456)
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return operatorNumbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }

}

class Subtract(symbol: String): Output(symbol){
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private var operatorNumbers = 0.0
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        var inputFormula = ""
        numbers = preOutput.numbersGetter()

        operatorNumbers = preOutput.operatorNumbersGetter()
        operators = Subtraction(operatorNumbers)

        val list = preOutput.getOperators( this)
        operatorList.addAll(list)

        operatorList.forEach { operator ->
            inputFormula += operator.formula()
        }

        numbers = 0.0

        return inputFormula // 123 + 456 -
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.removeAt(list.size - 1)
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun getOperators(): List<Operator> { // only number class use
        return operatorListGetter().toList()
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // Addition(123), Subtraction(456)
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return operatorNumbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }
}

class Multiple(symbol: String): Output(symbol){
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private var operatorNumbers = 0.0
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        var inputFormula = ""
        numbers = preOutput.numbersGetter()

        operatorNumbers = preOutput.operatorNumbersGetter()
        operators = Multiplication(operatorNumbers)

        val list = preOutput.getOperators( this)
        operatorList.addAll(list)

        operatorList.forEach { operator ->
            inputFormula += operator.formula()
        }

        numbers = 0.0

        return inputFormula // 123 + 456 *
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.removeAt(list.size - 1)
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun getOperators(): List<Operator> { // only number class use
        return operatorListGetter().toList()
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // Addition(123), Multiplication(456)
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return operatorNumbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }
}

class Divide(symbol: String): Output(symbol){
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private var operatorNumbers = 0.0
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        var inputFormula = ""
        numbers = preOutput.numbersGetter()

        operatorNumbers = preOutput.operatorNumbersGetter()
        operators = Division(operatorNumbers)

        val list = preOutput.getOperators(this)
        operatorList.addAll(list)

        operatorList.forEach { operator ->
            inputFormula += operator.formula()
        }

        numbers = 0.0

        return inputFormula // 123 + 456 /
    }

    override fun getOperators(nowOutput: Output): List<Operator> {
        val list: MutableList<Operator> = mutableListOf()
        list.addAll(operatorListGetter())
        list.removeAt(list.size - 1)
        list.add(nowOutput.operatorGetter())

        return list
    }

    override fun getOperators(): List<Operator> { // only number class use
        return operatorListGetter().toList()
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> { // Addition(123), Division(456)
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return operatorNumbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }
}

class EmptyOutput(default: String): Output(default){
    private var numbers = 0.0
    private val operatorList: MutableList<Operator> = mutableListOf()
    private lateinit var operators: Operator

    override fun output(preOutput: Output): String {
        numbers = 0.0
        operatorList.clear()
        return ""
    }

    override fun getOperators(): List<Operator> {
        return operatorList
    }


    override fun getOperators(nowOutput: Output): List<Operator> {
        return operatorListGetter().toList()
    }

    override fun numbersGetter(): Double {
        return numbers
    }

    override fun operatorListGetter(): List<Operator> {
        return operatorList
    }

    override fun operatorNumbersGetter(): Double {
        return numbers
    }

    override fun operatorGetter(): Operator {
        return operators
    }
}
