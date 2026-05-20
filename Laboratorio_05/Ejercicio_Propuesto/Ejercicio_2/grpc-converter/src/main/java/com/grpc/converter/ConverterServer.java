package com.grpc.converter;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class ConverterServer {

    static class ConverterService
            extends ConverterGrpc.ConverterImplBase {

        @Override
        public void convert(
                ConvertRequest request,
                StreamObserver<ConvertResponse> responseObserver) {

            double value = request.getValue();
            String type = request.getConversionType();

            double result = 0;
            String unit = "";

            // VALIDACIONES

            if (value < 0) {

                responseObserver.onError(
                        new IllegalArgumentException(
                                "No se permiten valores negativos"
                        )
                );

                return;
            }

            if (value > 1000000) {

                responseObserver.onError(
                        new IllegalArgumentException(
                                "Valor demasiado grande"
                        )
                );

                return;
            }

            // LOGS DEL SERVIDOR

            System.out.println("\n========== NUEVA SOLICITUD ==========");
            System.out.println("Solicitud recibida");
            System.out.println("Valor: " + value);
            System.out.println("Tipo: " + type);

            // CONVERSIONES

            switch (type.toLowerCase()) {

    case "celsius":
        result = (value * 1.8) + 32;
        unit = "Fahrenheit";
        break;

    case "soles":
        result = value / 3.7;
        unit = "Dolares";
        break;

    case "kilometros":
        result = value * 0.621371;
        unit = "Millas";
        break;

    case "metros":
        result = value * 3.28084;
        unit = "Pies";
        break;

    case "kg":
        result = value * 2.20462;
        unit = "Libras";
        break;

    case "litros":
        result = value * 0.264172;
        unit = "Galones";
        break;

    default:

        responseObserver.onError(
                new IllegalArgumentException(
                        "Tipo de conversion invalido"
                )
        );

        return;
}

            // CREAR RESPUESTA

            ConvertResponse response =
                    ConvertResponse.newBuilder()
                            .setResult(result)
                            .setMessage("Conversion exitosa")
                            .build();

            // MEMORIA USADA

            Runtime runtime = Runtime.getRuntime();

            long memory =
                    (runtime.totalMemory()
                            - runtime.freeMemory())
                            / (1024 * 1024);

            System.out.println(
                    "Memoria usada: "
                            + memory
                            + " MB"
            );

            // ENVIAR RESPUESTA

            responseObserver.onNext(response);

            responseObserver.onCompleted();

            System.out.println(
        "Resultado enviado: "
                + result
                + " "
                + unit
                 );
        }
    }
    public static void main(String[] args)
            throws IOException, InterruptedException {

        Server server = ServerBuilder
                .forPort(50051)
                .addService(new ConverterService())
                .build();

        server.start();

        System.out.println(
                "Servidor gRPC iniciado en puerto 50051"
        );

        server.awaitTermination();
    }
}