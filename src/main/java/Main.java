import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        ServicioAPI servicio = new ServicioAPI();
        Scanner scanner = new Scanner(System.in);

        boolean continuar = true;

        System.out.println("\n===================================");
        System.out.println("     Bienvenido al conversor       ");
        System.out.println("===================================\n");
        System.out.println("Formato del input: <MONEDA_ORIGEN> <MONEDA_DESTINO> <MONTO>");
        System.out.println("Ejemplo: USD ARS 100");
        System.out.println("Escribe 'SALIR' para terminar el programa.\n");

        while (continuar) {
            try {
                System.out.print("Ingrese la conversión (formato: ORIGEN DESTINO MONTO): ");
                String input = scanner.nextLine().trim();

                if (input.equalsIgnoreCase("SALIR")) {
                    System.out.println("\n¡Gracias por usar el conversor! Hasta pronto.\n");
                    continuar = false;
                    break;
                }

                String[] partes = input.split("\\s+");
                if (partes.length != 3) {
                    System.out.println("\nError: El formato debe ser <ORIGEN> <DESTINO> <MONTO>. Intente nuevamente.\n");
                    continue;
                }

                String monedaOrigen = partes[0].toUpperCase();
                String monedaDestino = partes[1].toUpperCase();
                double monto;

                try {
                    monto = Double.parseDouble(partes[2]);
                } catch (NumberFormatException e) {
                    System.out.println("\nError: El monto debe ser un número válido. Intente nuevamente.\n");
                    continue;
                }

                if (monto <= 0) {
                    System.out.println("\nEl monto debe ser mayor que 0. Intente nuevamente.\n");
                    continue;
                }

                // Validar monedas
                if (!esMonedaValida(monedaOrigen) || !esMonedaValida(monedaDestino)) {
                    System.out.println("\nError: Una o ambas monedas no son válidas. Intente nuevamente.\n");
                    continue;
                }

                // Realizar la conversión
                Monedas tasas = servicio.obtenerTasasDeCambio();
                double tasaDeCambio = servicio.obtenerTasaEspecifica(tasas, monedaOrigen, monedaDestino);
                double resultado = servicio.convertirMoneda(monto, tasaDeCambio);

                // Mostrar el resultado
                System.out.printf("\nEl monto convertido de %s %s a %s es: %.2f%n\n", monto,monedaOrigen, monedaDestino, resultado);

            } catch (Exception e) {
                System.out.println("\nOcurrió un error inesperado. Intente nuevamente.\n");
                e.printStackTrace();
            }
        }

        scanner.close();
    }

    /**
     * Verifica si una moneda es válida según la lista soportada.
     *
     * @param moneda Código de la moneda a validar.
     * @return True si la moneda es válida, de lo contrario false.
     */
    private static boolean esMonedaValida(String moneda) {
        return switch (moneda) {
            case "ARS", "USD", "BRL", "COP", "CLP", "BOB" -> true;
            default -> false;
        };
    }
}
