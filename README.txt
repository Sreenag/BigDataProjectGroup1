This is a scala program to use Arificial Neural Network to train Backpropagation(BP) algrorithm for forecasting
I didn't divided the input data into 90% for training and 10% for testing due to the fact that I don't have enough
input data to test.
Follow command is for issuing the scala program  
spark-submit --class grp1_ann --master local target/scala-2.10/big_data_group1_2.10-1.0.jar inputdata.txt