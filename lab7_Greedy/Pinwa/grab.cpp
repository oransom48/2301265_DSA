#include <bits/stdc++.h>
using namespace std;

// function ของการ backtrack ที่จะทำการหาคำตอบที่ดีที่สุด
// backtrack เป็น subset ของ brute force ที่จะทำการลองทุกๆ ทางเลือกที่เป็นไปได้ โดยจะหา Optimal solution ที่ดีที่สุด
void backtrack(int grabIndex,                              // ตำแหน่งของ Grab ที่จะทำการ assign ให้กับ Passenger
               const vector<int> &grabs,                   // ตำแหน่งของ Grab
               const vector<int> &passengers,              // ตำแหน่งของ Passenger
               int K,                                      // ระยะทางที่ Grab สามารถไปรับได้
               vector<bool> &assigned,                     // ตัวแปรเก็บว่า Passenger ไหนถูกรับแล้วบ้าง
               int currentCount,                           // จำนวนของ Passenger ที่ถูกรับไปแล้ว
               int &maxCount,                              // จำนวนของ Passenger ที่มากที่สุดที่ถูกรับไป
               int &countSolutions,                        // จำนวนของ solution ที่เป็น Optimal
               vector<int> &currentAssignment,             // รูปแบบของการรับผู้โดยสารของแต่ละ Grab, index ของ currentAssignment จะเป็น index ของ Grab, value คือ index ของ Passenger ที่ถูกรับไปด้วย Grab นั้นๆ เช่น currentAssignment[0] คือ Passenger ที่ถูกรับไปด้วย Grab ที่ 0
               vector<vector<int>> &allOptimalAssignments) // รูปแบบของการทั้งหมดของการรับผู้โดยสารของแต่ละ Grab ที่เป็น Optimal
{
    if (grabIndex == grabs.size()) // base case ของการ backtrack คือเมื่อทุก Grab ได้ทำการรับผู้โดยสารไปแล้ว
    {
        if (currentCount > maxCount) // ถ้าจำนวนของ Passenger ที่ถูกรับไปมากกว่าจำนวนของ Passenger ที่ถูกรับไปมากที่สุดที่เคยเจอ (เจอ solution ที่ดีที่สุดใหม่)
        {
            maxCount = currentCount;                            // จำนวนของ Passenger ที่ถูกรับไปมากที่สุดที่ถูกเจอเปลี่ยนเป็นจำนวนของ Passenger ที่ถูกรับไปในครั้งนี้
            countSolutions = 1;                                 // จำนวนของ solution ที่เป็น Optimal ก็จะเป็น 1 เพราะเราเจอ solution ที่ดีที่สุดใหม่
            allOptimalAssignments.clear();                      // เคลียร์ข้อมูลของการรับผู้โดยสารทั้งหมดที่เป็น Optimal ที่เคยเจอไว้
            allOptimalAssignments.push_back(currentAssignment); // เพิ่มข้อมูลของการรับผู้โดยสารที่เป็น Optimal ที่เจอไปในครั้งนี้
        }
        else if (currentCount == maxCount) // ถ้าจำนวนของ Passenger ที่ถูกรับไปเท่ากับจำนวนของ Passenger ที่ถูกรับไปมากที่สุดที่เคยเจอ
        {
            countSolutions++;                                   // เพิ่มจำนวนของ solution ที่เป็น Optimal ที่เจอไป
            allOptimalAssignments.push_back(currentAssignment); // เพิ่มข้อมูลของการรับผู้โดยสารที่เป็น Optimal ที่เจอไ
        }
        return;
    }

    // ทำการลองทุกๆ ทางเลือกที่เป็นไปได้
    bool assignedFlag = false;                  // ตัวแปรเก็บว่ามีการ assign ให้กับ Passenger ไหนไปแล้วหรือไม่
    for (int i = 0; i < passengers.size(); ++i) // ลองทุกๆ Passenger ที่เป็นไปได้
    {
        if (!assigned[i] && abs(grabs[grabIndex] - passengers[i]) <= K) // ถ้า Passenger ยังไม่ถูกรับไป และระยะทางระหว่าง Grab กับ Passenger น้อยกว่าหรือเท่ากับ K
        {
            assigned[i] = true;                           // ทำการ assign ให้ Passenger นี้ไป
            currentAssignment[grabIndex] = passengers[i]; // บันทึกการ assign ของ Grab นี้ไป Passenger นี้ เช่น currentAssignment[0] = 1 หมายถึง Grab ที่ 0 ได้รับ Passenger ที่ 1 ไป

            backtrack(grabIndex + 1, // ทำการ backtrack ต่อไป โดยเพิ่มจำนวนของ Passenger ที่ถูกรับไป 1 คน เลื่อนไปที่ Grab ถัดไป
                      grabs, passengers, K, assigned,
                      currentCount + 1, maxCount, // เพิ่มจำนวนของ Passenger ที่ถูกรับไป 1 คน
                      countSolutions, currentAssignment,
                      allOptimalAssignments);

            assigned[i] = false;               // ทำการ unassign ให้ Passenger นี้ไป ( นี่คือรูปแบบที่ทำให้วิธีนี้เรียกว่า backtrack โดยเราจะทำการลองให้ไปเส้นทางหนึ่งก่อน แล้วย้อนกลับมาลองเส้นทางอื่นๆ ณ การเลือกใดการเลือกหนึ่ง )
            currentAssignment[grabIndex] = -1; // บันทึกการ assign ของ Grab นี้ไป Passenger นี้เป็น -1 หมายถึง Grab นี้ไม่ได้รับ Passenger ไป
            assignedFlag = true;               // บอกว่ามีการ assign ให้ Passenger ไปแล้ว
        }
    }

    // ไปในกรณีที่ไม่ได้ assign Passenger ให้กับ Grab นี้เลย
    backtrack(grabIndex + 1, grabs, passengers, K, assigned, currentCount, maxCount, countSolutions, currentAssignment, allOptimalAssignments);
}

// Function to compute maximum passengers using brute force, count solutions, and collect all optimal assignments
pair<int, pair<int, vector<vector<int>>>> bruteForceSolution(const vector<int> &grabs, const vector<int> &passengers, int K)
{
    int maxCount = 0;
    int countSolutions = 0;
    vector<bool> assigned(passengers.size(), false);
    vector<int> currentAssignment(grabs.size(), -1); // -1 indicates no assignment
    vector<vector<int>> allOptimalAssignments;

    backtrack(0, grabs, passengers, K, assigned, 0, maxCount, countSolutions, currentAssignment, allOptimalAssignments);
    return {maxCount, {countSolutions, allOptimalAssignments}};
}

// Function to compute maximum passengers using greedy approach
int greedySolution(const vector<int> &grabs, const vector<int> &passengers, int K)
{
    int count = 0;
    int p = 0; // Pointer for Passengers

    for (int g = 0; g < grabs.size(); ++g)
    {
        while (p < passengers.size() && passengers[p] < grabs[g] - K)
        {
            p++;
        }
        if (p < passengers.size() && abs(grabs[g] - passengers[p]) <= K)
        {
            count++;
            p++;
        }
    }

    return count;
}

int main()
{
    string input;
    int K;

    // Prompt for input string
    cin >> input;
    cin >> K;

    // Extract positions of Grabs and Passengers
    vector<int> grabs;
    vector<int> passengers;
    for (int i = 0; i < input.length(); ++i)
    {
        if (input[i] == 'G')
        {
            grabs.push_back(i);
        }
        else if (input[i] == 'P')
        {
            passengers.push_back(i);
        }
        else
        {
            // Handle invalid characters
            cerr << "Invalid character '" << input[i] << "' in input. Only 'G' and 'P' are allowed.\n";
            return 1;
        }
    }

    // Brute Force Approach
    pair<int, pair<int, vector<vector<int>>>> bruteResult = bruteForceSolution(grabs, passengers, K);
    int maxPassengersBF = bruteResult.first;
    int numberOfSolutions = bruteResult.second.first;
    vector<vector<int>> allOptimalAssignments = bruteResult.second.second;

    // Greedy Approach
    // Sort the grabs and passengers positions
    vector<int> sortedGrabs = grabs;
    vector<int> sortedPassengers = passengers;
    sort(sortedGrabs.begin(), sortedGrabs.end());
    sort(sortedPassengers.begin(), sortedPassengers.end());

    int maxPassengersGreedy = greedySolution(sortedGrabs, sortedPassengers, K);

    // Output the results
    cout << "\n--- Brute Force Approach ---\n";
    cout << "Maximum number of passengers that can ride Grab: " << maxPassengersBF << "\n";
    cout << "Number of optimal solutions: " << numberOfSolutions << "\n";

    // Print all optimal assignments
    cout << "\nAll Optimal Assignments:\n";
    for (int s = 0; s < allOptimalAssignments.size(); ++s)
    {
        cout << "Solution " << s + 1 << ":\n";
        for (int g = 0; g < grabs.size(); ++g)
        {
            if (allOptimalAssignments[s][g] != -1)
            {
                cout << "  Grab at position " << grabs[g] << " picks Passenger at position " << allOptimalAssignments[s][g] << "\n";
            }
            else
            {
                cout << "  Grab at position " << grabs[g] << " is not assigned to any Passenger.\n";
            }
        }
        cout << "\n";
    }

    cout << "\n--- Greedy Approach ---\n";
    cout << "Maximum number of passengers that can ride Grab: " << maxPassengersGreedy << "\n";

    return 0;
}
