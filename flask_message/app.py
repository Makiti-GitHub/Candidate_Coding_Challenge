from flask import Flask, jsonify
import requests

app = Flask(__name__)

@app.route('/')
def hello():
    return 'Hello from Microservice 1!'

@app.route('/get_message_from_microservice2')
def get_message_from_microservice2():
    response = requests.get('http://microservice2:80/index.php')
    return jsonify({'message_from_microservice2': response.text})

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)