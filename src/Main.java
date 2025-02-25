import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

// Базовый интерфейс для геометрических фигур
interface Shape {
    double calculateArea();
    double calculatePerimeter();
}

// Класс круга
class Circle implements Shape {
    private double radius;

    public Circle(double radius) {
        if (radius <= 0) {
            throw new IllegalArgumentException("Радиус должен быть положительным числом");
        }
        this.radius = radius;
    }

    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * Math.PI * radius;
    }
}

// Класс квадрата
class Square implements Shape {
    private double side;

    public Square(double side) {
        if (side <= 0) {
            throw new IllegalArgumentException("Сторона должна быть положительным числом");
        }
        this.side = side;
    }

    @Override
    public double calculateArea() {
        return side * side;
    }

    @Override
    public double calculatePerimeter() {
        return 4 * side;
    }
}

// Класс прямоугольника
class Rectangle implements Shape {
    private double width;
    private double height;

    public Rectangle(double width, double height) {
        if (width <= 0 || height <= 0) {
            throw new IllegalArgumentException("Ширина и высота должны быть положительными числами");
        }
        this.width = width;
        this.height = height;
    }

    @Override
    public double calculateArea() {
        return width * height;
    }

    @Override
    public double calculatePerimeter() {
        return 2 * (width + height);
    }
}

// Пример класса для чтения данных и тестирования
class ShapeTest {
    public static void main(String[] args) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader("src/shapes.txt"));
            String line;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" ");
                String shapeType = parts[0];

                try {
                    switch (shapeType.toLowerCase()) {
                        case "circle":
                            double radius = Double.parseDouble(parts[1]);
                            Circle circle = new Circle(radius);
                            System.out.println("Круг: Площадь = " + circle.calculateArea() +
                                    ", Периметр = " + circle.calculatePerimeter());
                            break;

                        case "square":
                            double side = Double.parseDouble(parts[1]);
                            Square square = new Square(side);
                            System.out.println("Квадрат: Площадь = " + square.calculateArea() +
                                    ", Периметр = " + square.calculatePerimeter());
                            break;

                        case "rectangle":
                            double width = Double.parseDouble(parts[1]);
                            double height = Double.parseDouble(parts[2]);
                            Rectangle rectangle = new Rectangle(width, height);
                            System.out.println("Прямоугольник: Площадь = " + rectangle.calculateArea() +
                                    ", Периметр = " + rectangle.calculatePerimeter());
                            break;

                        default:
                            System.out.println("Неизвестный тип фигуры: " + shapeType);
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Ошибка в данных для " + shapeType + ": " + e.getMessage());
                }
            }
            reader.close();
        } catch (IOException e) {
            System.out.println("Ошибка при чтении файла: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Ошибка формата чисел в файле: " + e.getMessage());
        }
    }
}