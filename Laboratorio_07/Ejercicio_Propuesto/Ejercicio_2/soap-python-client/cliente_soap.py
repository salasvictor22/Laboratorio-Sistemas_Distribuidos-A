from zeep import Client
from zeep.exceptions import Fault, TransportError, XMLSyntaxError


def main():
    wsdl_url = "http://www.dneonline.com/calculator.asmx?WSDL"

    try:
        print("====================================")
        print(" CLIENTE SOAP CON PYTHON Y ZEEP")
        print("====================================")
        print(f"Conectando al servicio WSDL:")
        print(wsdl_url)
        print()

        client = Client(wsdl=wsdl_url)

        print("Servicio SOAP conectado correctamente.")
        print()

        a = 5
        b = 8

        suma = client.service.Add(a, b)
        resta = client.service.Subtract(a, b)
        multiplicacion = client.service.Multiply(a, b)
        division = client.service.Divide(a, b)

        print("Operaciones realizadas:")
        print("------------------------")
        print(f"Suma:           {a} + {b} = {suma}")
        print(f"Resta:          {a} - {b} = {resta}")
        print(f"Multiplicación: {a} * {b} = {multiplicacion}")
        print(f"División:       {a} / {b} = {division}")

    except Fault as error:
        print("Error SOAP recibido desde el servicio:")
        print(error)

    except TransportError as error:
        print("Error de conexión o transporte:")
        print(error)

    except XMLSyntaxError as error:
        print("Error al procesar la respuesta XML:")
        print(error)

    except Exception as error:
        print("Ocurrió un error inesperado:")
        print(error)


if __name__ == "__main__":
    main()