import requests
import json

API_KEY = "2b107iidPRdk9l8EmR9rhIteu"  # Your PlantNet API key
PROJECT = "all"  # Specify the project type
api_url = f"https://my-api.plantnet.org/v2/identify/{PROJECT}?api-key={API_KEY}"

def identify_plant(image_path):
    with open(image_path, 'rb') as image_data:
        files = {
            'images': (image_path, image_data),
        }
        data = {'organs': ['leaf']}  # Specify the plant organ type based on the image

        # Make the request to the PlantNet API
        response = requests.post(api_url, files=files, data=data)

        # Check if the request was successful
        if response.status_code == 200:
            json_result = json.loads(response.text)
            if 'results' in json_result and len(json_result['results']) > 0:
                plant_name = json_result['results'][0]['species']['scientificNameWithoutAuthor']
                print(plant_name)
                return plant_name
            else:
                return "No results found."
        else:
            print(f"Error: {response.status_code}, {response.text}")
            return None
