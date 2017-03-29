'''
Created on Mar 29, 2017

@author: Zuqiang Ke
'''
import eia


api_key = 'your api id'
api = eia.API(api_key)

ssign = int(input('by category(1)/series(2)? '))
fname = input('data file name? ')
fp = open(fname,'w');
#Average retail price of electricity:40
if ssign == 1:
     category = input('category num :')  #Residential:1012/Commercial:1013/Industrial:1014/Transportation:1015/Other:1016
     category_search = api.data_by_category(category=category)
     for key,value in category_search.items():
      # print(key,value)
       fp.write('%s\t%s' % (key,value))


if ssign == 2:
    series1 = input('series id :') #Average retail price of electricity : Alabama : other : annual: ELEC.PRICE.AL-OTH.A
    series_search = api.data_by_series(series=series1)
    for key,value in series_search.items():
        #print(key,value)
        fp.write('%s\t%s' % (key,value))
fp.close()