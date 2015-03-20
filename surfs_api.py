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


# get_images_sw
@app.route('/', methods=['GET'])
def get_picture():

    # index = 0
    # amount = 2
    # c = '01'
    # while index < amount:     # index + 1     # c + '1'

    urls = []
    url_1 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_001.png'
    url_2 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_002.png'
    url_3 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_003.png'
    url_4 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_004.png'
    url_5 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_005.png'
    url_6 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_006.png'
    url_7 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_007.png'
    url_8 = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_008.png'

    urls.append(url_1)
    urls.append(url_2)
    urls.append(url_3)
    urls.append(url_4)
    urls.append(url_5)
    urls.append(url_6)
    urls.append(url_7)
    urls.append(url_8)

    return jsonify({'urls': urls})

    '''
    url_1 = "http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_001.png"
    response_1 = requests.get(url_1)

    url_2 = "http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_002.png"
    response_2 = requests.get(url_2)

    url_3 = "http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_003.png"
    response_3 = requests.get(url_3)

    urls = [response_1, response_2, response_3]
    '''

    '''
    urls.append(response_1)
    urls.append(response_2)
    urls.append(response_3)

    urls.append({

        'sw_1': response_1,
        'sw_2': response_2,
        'sw_3': response_3
    })
    '''

    '''
    'sw_1': 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_001.png',
    'sw_2': 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_002.png',
    'sw_3': 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_frame_003.png'
    '''



@app.route('/marine_forecast_data', methods=['GET'])
def get_marine_inst_forecast_data():

    url = 'http://erddap2.marine.ie/erddap/tabledap/IWBNetwork.json?station_id,time,WindDirection,WindSpeed,Gust,' \
          'WaveHeight,WavePeriod,MeanWaveDirection&time>=2015-03-07T00:00:00Z'

    response = requests.get(url)

    data = json.loads(response.content)

    return jsonify(data), 200


@app.route('/test')
def test():
    url = "http://erddap2.marine.ie/erddap/tabledap/IWBNetwork.json?station_id,time,WindDirection,WindSpeed,Gust," \
          "WaveHeight,WavePeriod,MeanWaveDirection&time>=2015-03-07T00:00:00Z"

    r = requests.get(url)
    data = json.loads(r.content)

    # parsed_data = []

    station_id_m2 = get_station_m2_data(data)

    # station_id_m3 = get_station_m3_data(data)

    # station_id_m4 = get_station_m4_data(data)

    # station_id_m5 = get_station_m5_data(data)

    return jsonify({'data': station_id_m2[234]}), 200


def get_station_m2_data(m2_data):

    m2 = []

    for row in m2_data['table']['rows']:

        if row[0] == 'M2':

            m2.append({
                'station_id': row[0],
                'time': row[1],
                'WindDirection': row[2],
                'WindSpeed': row[3],
                'Gust': row[4],
                'WaveHeight': row[5],
                'WavePeriod': row[6],
                'MeanWaveDirection': row[7]
            })

    return m2


@app.route('/marine_tidal_data', methods=['GET'])
def get_marine_inst_tidal_data():

    url = 'http://erddap2.marine.ie/erddap/tabledap/IrishNationalTideGaugeNetwork.json?time,station_id,Water_Level,' \
          'Water_Level_LAT,Water_Level_OD_Malin&time>=2015-03-08T00:00:00Z'

    response = requests.get(url)
    data = json.loads(response.content)

    return jsonify(data)


@app.route('/surfs/api/v1.0/spots', methods=['GET'])
def get_spots():

    return jsonify(spots_array)


@app.route('/surfs/api/v1.0/favourite_spots', methods=['GET'])
def get_fav_spots():

    return jsonify({'favourite_spots': favourite_spots})


@app.route('/main')
def main():

    return 'Hello, World!'


if __name__ == '__main__':
    app.run()


