from flask import Flask, jsonify
import requests

try:
    import simplejson as json
except ImportError:
    import json


app = Flask(__name__)


favourite_spots = [
    {
        'County': 'Donegal',
        'Spot': 'The Peak',
        'Tide': 'Low',
        'Swell Dir': 'W',
        'Swell Reported': 4,
        'Wind Dir': 'E',
        'Wind Speed': 8,
        'Swell Period': 12
    }
]


spots_array = {
    'spots': ['Achill Keel Beach' , 'Ballybunnion', 'Brandon Bay', 'Bundoran The Peak', 'Carrownisky', 'Castlegregory Dumps',
             'Dunfanaghy', 'Easkey Left', 'Easkey Right', 'Enniscrone', 'Inch Reef', 'Inch Strand', 'Inchydoney', 'Lahinch',
         'Mossies', 'Muckross', 'Mullaghmore Strand', 'North Malin', 'Pollan Bay', 'Rossnowlagh', 'Strandhill',
         'Tranmore', 'Mullaghmore']
}


@app.route('/test_pic', methods=['GET'])
def get_picture():

    url = "http://erddap.marine.ie/erddap/griddap/IMI_EATL_WAVE.png?significant_wave_height[(2015-03-14T21:00:00Z)][(36.5125):(59.987500000000004)][(-19.9875):(-0.01249999999999929)],swell_wave_height[(2015-03-14T21:00:00Z)][(36.5125):(59.987500000000004)][(-19.9875):(-0.01249999999999929)]&.draw=vectors&.vars=longitude|latitude|significant_wave_height|swell_wave_height&.color=0x000000.png"

    # response = requests.get(url)
    # data = json.loads(response.content)
    # jsonify(data)

    return url


@app.route('/marine_data', methods=['GET'])
def get_marine_inst_data():

    url = 'http://erddap.marine.ie/erddap/tabledap/IWBNetwork.json?station_id,longitude,latitude,time,' \
          'AtmosphericPressure,WindDirection,WindSpeed,Gust,WaveHeight,WavePeriod,MeanWaveDirection,Hmax,' \
          'AirTemperature,DewPoint,SeaTemperature,salinity,RelativeHumidity,QC_Flag&time>=2015-01-19T00:00:00Z'

    response = requests.get(url)
    data = json.loads(response.content)

    return jsonify(data)


@app.route('/surfs/api/v1.0/spots', methods=['GET'])
def get_spots():

    return jsonify(spots_array)


@app.route('/surfs/api/v1.0/favourite_spots', methods=['GET'])
def get_fav_spots():

    return jsonify({'favourite_spots': favourite_spots})


@app.route('/')
def main():

    return 'Hello, World!'


if __name__ == '__main__':
    app.run()


