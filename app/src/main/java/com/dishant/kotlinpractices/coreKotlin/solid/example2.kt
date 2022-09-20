package com.dishant.kotlinpractices.coreKotlin.solid

import java.lang.RuntimeException

// open-close principle


// Open : This means that we can add new feature to our classes.
// When there is a new requirement, we should be add feature to our class easily


//Close : This means that the base feature of the class should not be changed.

class Rectangle{
    private  var  length : Double? = 12.0
    private  var  height : Double? = 8.0

    fun getLength() : Double?{
        return length
    }

    fun getHeight() : Double?{
        return height
    }
}

class  Circle{
    fun getRadius(): Double {
        return  radius
    }

    private var radius : Double = 5.0
}

class AreaFactory{
    fun calculateArea(shapes : ArrayList<Any>): Double {
        var area =0.0
        for (shape in shapes){
            if(shape is Rectangle){
                val rect = shape as Rectangle
                area += (rect.getLength()!!.times(rect.getHeight()!!))
            }else if(shape is Circle){
                val circle = shape as Circle
                area += circle.getRadius().times(circle.getRadius()).times(Math.PI)
            }else {
                throw RuntimeException("Shape not supported")
            }
        }
        return area
    }
}

// fix above issue by add common interface in shape classes and calculate are within class without change its base code

interface  Shape{
    fun getArea() : Double
}


class Rectangle1 : Shape{
    private var length = 12.33
    private var height = 10.55

    override fun getArea() : Double {
        return  length.times(height)
    }
}

class Circle1 : Shape{
    private var radius =20.99
    override fun getArea(): Double {
        return radius.times(radius).times(Math.PI)
    }
}

class AreaFactory1{
    fun calculateArea(shapes: ArrayList<Shape>): Double {
        var area = 0.0
        for (shape in shapes) {
            area += shape.getArea()
        }
        return area
    }
}

