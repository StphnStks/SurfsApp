from flask import Flask, jsonify
import requests

try:
    import simplejson as json
except ImportError:
    import json


app = Flask(__name__)


spots_array = {
    'spots': ['Achill Keel Beach' , 'Ballybunnion', 'Brandon Bay', 'Bundoran The Peak', 'Carrownisky', 'Castlegregory Dumps',
             'Dunfanaghy', 'Easkey Left', 'Easkey Right', 'Enniscrone', 'Inch Reef', 'Inch Strand', 'Inchydoney', 'Lahinch',
         'Mossies', 'Muckross', 'Mullaghmore Strand', 'North Malin', 'Pollan Bay', 'Rossnowlagh', 'Strandhill',
         'Tranmore', 'Mullaghmore']
}


# get images swell height
@app.route('/', methods=['GET'])
def get_wave_height_charts():

    url = 'http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_gb/sw_frame_'
    wave_height = []

    for i in range(1, 50):
        new_url = url + "{:03d}".format(i) + ".png"
        wave_height.append(new_url)
        i += 1

    return jsonify({'urls': wave_height})


# get images wave period and direction
@app.route('/1', methods=['GET'])
def get_wave_period_dir_charts():

    url = "http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_gb/wp_frame_"
    wave_period_dir = []

    for i in range(1, 50):

        new_url = url + "{:03d}".format(i) + ".png"
        wave_period_dir.append(new_url)
        i += 1

    return jsonify({'urls_wp': wave_period_dir})


# get images wind speed and direction
@app.route('/2', methods=['GET'])
def get_wind_speed_dir_charts():

    url = "http://webapps.marine.ie/CMSWeb/mi/OSS/images/sw/sw_gb/ws_frame_"
    wind_speed_dir = []

    for i in range(1, 50):

        new_url = url + "{:03d}".format(i) + ".png"
        wind_speed_dir.append(new_url)
        i += 1

    return jsonify({'urls_ws_dir': wind_speed_dir})


'''
@app.route('/marine_forecast_data', methods=['GET'])
def get_marine_inst_forecast_data():

    url = 'http://erddap2.marine.ie/erddap/tabledap/IWBNetwork.json?station_id,time,WindDirection,WindSpeed,Gust,' \
          'WaveHeight,WavePeriod,MeanWaveDirection&time>=2015-03-07T00:00:00Z'

    response = requests.get(url)

    data = json.loads(response.content)

    return jsonify(data), 200
'''


# get forecast data
@app.route('/test')
def test():

    url = "http://erddap2.marine.ie/erddap/tabledap/IWBNetwork.json?station_id,time,WindDirection,WindSpeed,Gust," \
          "WaveHeight,WavePeriod,MeanWaveDirection&time>=2015-03-07T00:00:00Z"

    ''' New Url 6 days in advance forecast excluding wind conditions


       'http://erddap.marine.ie/erddap/tabledap/IMI-WaveBuoyForecast.json?time,longitude,latitude,stationID,significant_wave_height,mean_wave_period,mean_wave_direction&time>=2015-03-14T00:00:00Z'
    '''

    r = requests.get(url)
    data = json.loads(r.content)

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


@app.route('/main')
def main():

    return 'Hello, World!'


if __name__ == '__main__':
    app.run()


