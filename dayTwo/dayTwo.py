'''
DAY TWO:
done last day of thanksgiving break at 11pm!!

THOUGHTS: This would have been so much faster if I did java, but i wanted to try my hand at python since I am ostensibly supposed to 
be in progress learning it. Python is so weird to me now! What do you mean you don't have to define type and return values in functions?
WTF IS UP WITH THE STUPID INDENTING THING??? (I wasted so much time fiddling with that). I think I understand python a little better now.
I think if I was better at it I would appreciate its ease but as it is I spent more time on stackoverflow than thinking through this
problem. Which was pretty easy...

'''
def main():
    # Not going to lie, I wish declaring variables was this easy in cs300
    report = []
    numberSafeNoChange = 0

    # DO NOT UNDERSTAND THIS AT ALL (even less than java bufferedreader). this file part was lifted straight from stackoverflow
    with open('C:\\Users\\Sheryl\\Downloads\\inputDay2.txt','r') as f:
        for line in f: # i like python for loops
            for word in line.split(" "): # .split() is similar to java thank god
                report.append(int(word.strip('\n'))) # i guess there is no array/arraylist distinction??
            if (decreasingOrIncreasing(report) & differLevels(report)):
                numberSafeNoChange+=1 # no ++; function either which is weird considering how much else there is in terms of functions
            report.clear() # this function SLAYS
    print("\nNumber Safe: ") # idk if python has a println or not. it doesn't seem to have concatenation (or i didn't google hard enough) which is weird
    print(numberSafeNoChange)

    numSafeOneRemoved = 0 # part one was pretty easy once i figured out the python of it all
    report = []
    with open('C:\\Users\\Sheryl\\Downloads\\inputDay2.txt','r') as d:
        for line in d:
            for word in line.split(" "):
                report.append(int(word.strip('\n'))) 
            # for each report, check to see if the report is SAFE with first # removed, repeat w all #s in report
            if trueOneRemoved(report):
                numSafeOneRemoved+=1
            report.clear()
    print("\nNumber Safe with one removed: ")
    print(numSafeOneRemoved)

    numSafeTotal = numSafeOneRemoved + numberSafeNoChange
    print("\nNumber Safe total: ")
    print(numSafeTotal)

def decreasingOrIncreasing(arr):
    if arr[0]<arr[1]: # array must be increasing
        for i in range(len(arr)-1):
            if arr[i]>=arr[i+1]: # if array is ever not increasing then return false
                return False
        return True
    if arr[0]>arr[1]: # array must be decreasing, same logic as first if statement
        for i in range(len(arr)-1):
            if arr[i]<=arr[i+1]:
                return False 
        return True
    return False # two values at front of array are equal or some other weird thing so return false

def differLevels(arr):
    for i in range(len(arr)-1):
        diff = arr[i]-arr[i+1]
        if (abs(diff)<1) | (abs(diff)>3): # i like that abs value is its own built in function here
            return False
    return True 

def trueOneRemoved(report): 
    for i in range(len(report)):
            reportRemoved = [] # create deep copy of report array 
            for j in range(len(report)):
                reportRemoved.append(report[j])
            reportRemoved.pop(i) # remove one number from copy (loop increments through all numbers of array for temporary removal and verification)
            if (decreasingOrIncreasing(reportRemoved) & differLevels(reportRemoved)): # check to see if array is now safe with one num removed
                return True
    return False

if __name__=="__main__": # THIS MAIN FUNCTION THING IS SO WACKY TO ME
    main()