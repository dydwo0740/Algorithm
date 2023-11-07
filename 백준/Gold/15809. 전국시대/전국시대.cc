#include <iostream>
#include <vector>
#include <set>

using namespace std;

vector<int> parent;
vector<int> num;

void    init(int n)
{
    parent.resize(n + 1);
    num.resize(n + 1);
    
    for (int i = 1; i <= n; i++)
        parent[i] = i;
}

int     find(int x)
{
    if (x == parent[x])
        return x;
    return (parent[x] = find(parent[x]));
}

void    merge1(int x, int y)
{
    x = find(x);
    y = find(y);
    
    num[y] += num[x];
    parent[x] = y;
}

void    merge2(int x, int y)
{
    x = find(x);
    y = find(y);
    
    if (num[x] > num[y])
    {
        num[x] -= num[y];
        parent[y] = x;
    }
    else if (num[x] < num[y])
    {
        num[y] -= num[x];
        parent[x] = y;
    }
    else
    {
        num[x] = 0;
        num[y] = 0;
    }
}

int     main()
{
    int n, m;
    scanf("%d %d", &n, &m);
    init(n);
    
    for (int i = 1; i <= n; i++)
    {
        scanf("%d", &num[i]);
    }
    
    for (int i = 0; i < m; i++)
    {
        int o, p, q;
        scanf("%d %d %d", &o, &p, &q);
        
        if (o == 1)
            merge1(p, q);
        else
            merge2(p, q);
    }
   
    multiset<int> s;
    for (int i = 1; i <= n; i++)
    {
        if (i == parent[i] && num[i] != 0)
            s.insert(num[i]);
    }
    
    cout << s.size() << endl;
    for (auto n : s)
    {
        cout << n << " ";
    }
    
    return (0);
}