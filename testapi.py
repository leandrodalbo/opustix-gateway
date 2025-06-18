from flask import Flask, jsonify, request

app = Flask(__name__)

@app.route("/ticketera/public/test")
def public_endpoint():
    print("🔓 Public Endpoint Hit")
    print("📦 Headers:", dict(request.headers))
    return jsonify(message="Public content - no authentication required.")

@app.route("/ticketera/private/test")
def private_endpoint():
    auth_header = request.headers.get("Authorization", "")
    print("🔐 Private Endpoint Hit")
    print("📦 Headers:", dict(request.headers))

    if not auth_header.startswith("Bearer "):
        return jsonify(error="Unauthorized - no valid token"), 401

    return jsonify(message="Private content - token accepted.")

@app.route("/ticketera/echo")
def echo_headers():
    return jsonify(headers=dict(request.headers))

if __name__ == "__main__":
    app.run(host="0.0.0.0", port=8082)

