from flask import Flask, jsonify
app = Flask(__name__)

@app.route("/api/proxy")
def proxy():
    return jsonify(message="Hello from dummy event-gateway!")

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8080)
