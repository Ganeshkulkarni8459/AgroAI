# from flask import app, request
# import integrate  # Ensure this imports your PlantNet integration module
# import check_deep  # Assuming this contains your disease prediction functions

# def identify_disease(plant_name, image_path):
#     """Identify the disease based on the plant name."""
#     if plant_name == 'Gossypium herbaceum L.':  # cotton
#         return check_deep.predict_cotton_disease(image_path)
#     elif plant_name == 'Soybean':  # soybean
#         return check_deep.predict_soybean_disease(image_path)
#     elif plant_name == 'Saccharum officinarum':  # sugarcane
#         return check_deep.predict_sugarcane_disease(image_path)
#     # Add more conditions as necessary
#     else:
#         return {'error': 'No disease prediction model available for this plant'}

# if __name__ == "__main__":
#     image_path = r"C:\Users\Atul\Pictures\my_image.jpg"  # Replace with the path to your image
   

#     # Identify the plant
#     plant_name = integrate.identify_plant(image_path)

#     # Print the result
#     if plant_name:
#         print(f"Identified Plant: {plant_name}")

#         # Predict the disease
#         disease_result = identify_disease(plant_name, image_path)

#         if isinstance(disease_result, dict) and 'error' in disease_result:
#             print(f"Disease Prediction Error: {disease_result['error']}")
#         else:
#             print(f"Disease Detected: {disease_result['disease']}, Confidence: {disease_result['confidence']}")
#     else:
#         print("Plant identification failed.")



import os
import traceback
from flask import Flask, request, jsonify
import werkzeug
import integrate  # Ensure this imports your PlantNet integration module
import check_deep  # Assuming this contains your disease prediction functions

app = Flask(__name__)

# Create a directory for uploaded images if it doesn't exist
UPLOAD_FOLDER = './uploadedimages'
if not os.path.exists(UPLOAD_FOLDER):
    os.makedirs(UPLOAD_FOLDER)

@app.route('/identify', methods=['POST'])
def identify():
    # Check if 'image' is present in the request files
    if 'image' not in request.files:
        return jsonify({'error': 'No image file provided.'}), 401

    # Get the image file from the POST request
    image_file = request.files['image']
    
    # Secure the filename and save the image
    filename = werkzeug.utils.secure_filename(image_file.filename)
    image_path = os.path.join(UPLOAD_FOLDER, filename)
    image_file.save(image_path)
    
    try:
        # Identify the plant using the saved image
        plant_name = integrate.identify_plant(image_path)

        if not plant_name:
            return {'error': 'Crop not detected. Please upload a different image'}

        # Predict the disease based on the identified plant
        disease_result = identify_disease(plant_name, image_path)
        print(f"Disease result: {disease_result}")
                # Ensure disease_result is a dictionary and JSON serializable
        if not isinstance(disease_result, dict):
            return jsonify({'error': 'Unexpected result format from disease prediction.'}), 500

        # Return the disease result as a JSON response
        return jsonify(disease_result)

    except Exception as e:
        error_trace = traceback.format_exc()
        print(f"Error occurred: {error_trace}")
        return jsonify({'error': f'Failed to process image: {str(e)}'}), 500

def identify_disease(plant_name, image_path):
    """Identify the disease based on the plant name."""
    if plant_name == 'Gossypium herbaceum L.':  # Cotton
        return check_deep.predict_cotton_disease(image_path)
    elif plant_name == 'Phaseolus lunatus':  # Soybean
        return check_deep.predict_soybean_disease(image_path)
    elif plant_name == 'Saccharum officinarum':  # Sugarcane
        return check_deep.predict_sugarcane_disease(image_path)
    else:
        return {'error': 'No disease prediction model available for this plant'}

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
