from flask import Flask, request, jsonify

app = Flask(__name__)

# Lista temporal de estudiantes
estudiantes = []

# GET -> listar estudiantes
@app.route('/estudiantes', methods=['GET'])
def listar_estudiantes():
    return jsonify(estudiantes)

# POST -> agregar estudiante
@app.route('/estudiantes', methods=['POST'])
def agregar_estudiante():

    nuevo = request.json

    estudiantes.append(nuevo)

    return jsonify({
        "mensaje": "Estudiante agregado correctamente",
        "datos": nuevo
    })

# PUT -> actualizar estudiante
@app.route('/estudiantes/<int:id>', methods=['PUT'])
def actualizar_estudiante(id):

    if id >= len(estudiantes):

        return jsonify({
            "error": "Estudiante no encontrado"
        }), 404

    estudiantes[id] = request.json

    return jsonify({
        "mensaje": "Estudiante actualizado"
    })

# DELETE -> eliminar estudiante
@app.route('/estudiantes/<int:id>', methods=['DELETE'])
def eliminar_estudiante(id):

    if id >= len(estudiantes):

        return jsonify({
            "error": "Estudiante no encontrado"
        }), 404

    eliminado = estudiantes.pop(id)

    return jsonify({
        "mensaje": "Estudiante eliminado",
        "datos": eliminado
    })

# Ejecutar API
if __name__ == '__main__':
    app.run(debug=True)