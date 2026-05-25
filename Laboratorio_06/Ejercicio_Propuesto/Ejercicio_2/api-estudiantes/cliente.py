import requests

url = "http://127.0.0.1:5000/estudiantes"

# Nuevo estudiante
nuevo_estudiante = {
    "nombre": "Carlos",
    "edad": 22,
    "carrera": "Software"
}

# POST -> agregar estudiante
respuesta = requests.post(url, json=nuevo_estudiante)

print("POST:")
print(respuesta.json())

# GET -> consultar estudiantes
respuesta = requests.get(url)

print("\nGET:")
print(respuesta.json())