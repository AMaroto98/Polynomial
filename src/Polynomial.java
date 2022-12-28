import javax.imageio.metadata.IIOMetadataFormatImpl;
import java.sql.Array;
import java.util.Arrays;
import java.util.Objects;

public class Polynomial {


    public float[] coeficiente;

    // Constructor per defecte. Genera un polinomi zero
    public Polynomial() {
        this.coeficiente = new float[]{0};
    }

    // Constructor a partir dels coeficients del polinomi en forma d'array (más fácil)
    public Polynomial(float[] cfs) {
        this.coeficiente = cfs;
    }

    // Constructor a partir d'un string (Más difícil)
    public Polynomial(String s) {

        // Polinomio de ejemplo
        // 5x^7 + 5x^4
        // [5x^7, + ,5x^4]

        // Variable elevadoMaximo en el que almacenaremos el elevado más grande del polinomio.
        int elevadoMaximo = 0;

        // Variable String[] en la cual almacenaremos los polinomios separados por espacios.
        // [5x^7] [5x^4]
        // [5x, 7]  [5x, 4]
        String[] arraySeparadaEspacios = s.split(" ");

        for (int i = 0; i < arraySeparadaEspacios.length; i++) {

            // Volvemos a separar los valores de la array con un Split para separar el coeficiente del elevado.
            String valor = arraySeparadaEspacios[i];
            String[] arraySeparadaElevado = valor.split("\\^");

            // Hacemos el if para
            if (arraySeparadaElevado.length > 1) {

                // [5x, 7] [5x, 4]
                // int elevado = 7 y luego int elevado = 4.
                // Se comprueba luego cual es el elevado máximo para construir los polinomios, es decir, si el valor elevado máximo es 7 quiere decir que habrá 6 numeros más detras de él.
                // El indice 1 es el elevado pues es donde queda almacenado en la array

                int elevado = Integer.parseInt(arraySeparadaElevado[1]);

                // Condición para setear elevado máximo para saber el polinimio de mayor elevado.

                if (elevado > elevadoMaximo) {

                    elevadoMaximo = elevado;

                }

                // El else es simplemente porque si el polinomio es 4x es elevado a 1.

            } else if (arraySeparadaElevado[0].contains("x") && 1 > elevadoMaximo) {

                elevadoMaximo = 1;

            }
        }

        // Rellenamos this.coeficiente con valores por defecto, en este caso el 0.
        // Es decir, si tenemos {2x^2 + 5x + 7} nos lo ponga como {0,0,0}

        this.coeficiente = new float[elevadoMaximo + 1];

        for (int i = 0; i < this.coeficiente.length; i++) {

            this.coeficiente[i] = 0;

        }

        // Aquí SE SACA EL ELEVADO. Para sacar la posición en la que colocamos todos los polinomios restamos el elevado al elevado máximo y podemos saber donde colocar cada polinomio

        for (int i = 0; i < arraySeparadaEspacios.length; i++) {

            String temporal = arraySeparadaEspacios[i];
            String[] arraySeparadoElevado = temporal.split("\\^");
            int elevado = 0;

            if (arraySeparadoElevado.length > 1) {

                elevado = Integer.parseInt(arraySeparadoElevado[1]);

            } else if (arraySeparadaEspacios[i].contains("x")) {

                elevado = 1;
            }

            // 4x^2 + 5x^3
            // [4x,2 + 5x,3]

            if (!temporal.equals("+") && !temporal.equals("-")) {

                String[] arraySeparadoNumero = temporal.split("x");

                int coeficienteSeparado = 0;


                if (arraySeparadoElevado[0].equals("x")) {

                    coeficienteSeparado = 1;

                } else if (arraySeparadoElevado[0].equals("-x")) {

                    coeficienteSeparado = -1;

                } else {

                    coeficienteSeparado = Integer.parseInt(arraySeparadoNumero[0]);
                }
                // Se miran los signos
                if (i != 0) {
                    String signo = arraySeparadaEspacios[i - 1];

                    if (signo.equals("-")) {

                        coeficienteSeparado *= -1;
                    }
                }
                // Sacamos indice actual, Tenemos: 2x^3 + 3x^2 + 4x + 3

                // Indice actual = 3 - 3 = 0.

                int indiceActual = elevadoMaximo - elevado;

                // {0,0,0,0} --> Añadimos los valores, en este caso el coeficiente que hemos separado anteriormente

                this.coeficiente[indiceActual] += coeficienteSeparado;

            }
        }
    }

    // Suma el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial add(Polynomial p) {

        // [4x^3, 5x^2 ,6x ,7]
        // [2x^2, 3x, 5]

        float[] arrayMenor = p.coeficiente.length > this.coeficiente.length ? this.coeficiente : p.coeficiente;
        float[] arrayMayor = p.coeficiente.length <= this.coeficiente.length ? this.coeficiente : p.coeficiente;
        int diferencia = arrayMayor.length - arrayMenor.length;

        for (int i = 0; i < arrayMenor.length; i++) {

            float valor = arrayMenor[i];

            arrayMayor[i + diferencia] += valor;

        }

       return new Polynomial(arrayMayor);
    }

    // Multiplica el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    public Polynomial mult(Polynomial p2) {
        return null;
    }

    // Divideix el polinomi amb un altre. No modifica el polinomi actual (this). Genera un de nou
    // Torna el quocient i també el residu (ambdós polinomis)
    public Polynomial[] div(Polynomial p2) {
        return null;
    }

    // Troba les arrels del polinomi, ordenades de menor a major
    public float[] roots() {
        return null;
    }

    // Torna "true" si els polinomis són iguals. Això és un override d'un mètode de la classe Object
    @Override
    public boolean equals(Object o) {
        Polynomial p = (Polynomial) o;
        Polynomial p2 = this;

        return p.toString().equals(p2.toString());
    }

    // Torna la representació en forma de String del polinomi. Override d'un mètode de la classe Object
    @Override
    public String toString() {

        // Condición para devolver un 0 en el caso de que el coeficiente sea nulo.

        if (this.coeficiente == null) {

            return "0";
        }

        // Inicio Booleano soloNumeros. Puesto para arreglar el constructor2.
        // Si algún coeficiente es diferente de 0 es verdadero y se lo salta, si es true devuelve pintado un 0.

        boolean soloNumeros = false;

        for (int i = 0; i < coeficiente.length; i++) {

            soloNumeros = coeficiente[i] != 0;

            if (soloNumeros == true)
                break;
        }

        if (soloNumeros == false) {

            return "0";
        }

        // Final Booleano soloNumeros

        // Polinomio que setearemos con el resultado final.
        String polinomio = "";

        for (int i = 0; i < coeficiente.length; i++) {
            int elevado = coeficiente.length - 1 - i;
            float valor = this.coeficiente[i];

            if (polinomio.length() > 0) {

                polinomio += i != 0 && valor < 0 ? " - " : i != 0 && valor != 0 ? " + " : "";

            }

            if ((valor != 1 && valor != -1) || coeficiente.length - 1 == i) {

                if (valor != 0) {
                    float positivo = valor < 0 && i != 0 ? valor * -1 : valor;

                    polinomio += String.valueOf((int) positivo);
                }
            }

            if (i != this.coeficiente.length - 1 && valor != 0 && elevado != 1) {

                if (valor == -1 && polinomio.length() == 0) {

                    polinomio += "-x^" + elevado;

                } else {

                    polinomio += "x^" + elevado;
                }
            } else if (elevado != 0 && valor != 0 ) {

                if (valor == 1 || elevado == 1) {

                    polinomio += "x";

                } else if (valor == -1) {

                    polinomio += "-x";

                }
            }
        }

        // Para que era esto?
        String[] polinomioSplit = polinomio.split(" ");
        if (polinomioSplit.length == 2) {
            polinomio = polinomioSplit[1];
        }
        return polinomio;
    }
}