package com.grpc.converter;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class ConverterClient {

    public static void main(String[] args) {

        ManagedChannel channel = ManagedChannelBuilder
                .forAddress("localhost", 50051)
                .usePlaintext()
                .build();

        ConverterGrpc.ConverterBlockingStub stub =
                ConverterGrpc.newBlockingStub(channel);

        probarConversion(stub, 100, "celsius");
        probarConversion(stub, 370, "soles");
        probarConversion(stub, 10, "kilometros");
        probarConversion(stub, 5, "kg");

        channel.shutdown();
    }

    public static void probarConversion(
            ConverterGrpc.ConverterBlockingStub stub,
            double valor,
            String tipo) {

        try {

            ConvertRequest request =
                    ConvertRequest.newBuilder()
                            .setValue(valor)
                            .setConversionType(tipo)
                            .build();

            long inicio = System.currentTimeMillis();

            ConvertResponse response =
                    stub.convert(request);

            long fin = System.currentTimeMillis();

            System.out.println("\nConversion: " + tipo);
            System.out.println("Valor enviado: " + valor);
            System.out.println("Resultado: " + response.getResult());
            System.out.println("Tiempo: " + (fin - inicio) + " ms");

        } catch (Exception e) {

            System.out.println("Error en conversion");
            System.out.println(e.getMessage());
        }
    }
}