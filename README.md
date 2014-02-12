cuckoo [![Build Status](https://travis-ci.org/eug/cuckoo.png?branch=master)](https://travis-ci.org/eug/cuckoo)
======

A simple and easy-to-use framework for creating automatons..

  - Finite, Pushdown and Turing machine support.
  - Descriptive declarations
  - Implemented with breadth-first search for backtracking


Build
----------
The ```*.jar``` file will be created at ```dist``` directory

```sh
ant -f build.xml
```


----------
TL;DR docs
----------
#### State classes ####
* ``` FState ```: Finite State
* ``` PState ```: Pushdown State
* ``` TState ```: Turing machine State

#### Runner classes ####
* ``` DFARunner ```:  Deterministic Finite Automaton
* ``` NFARunner ```:  Non-Deterministic Finite Automaton
* ``` DPDARunner ```: Deterministic Pushdown Automaton
* ``` NPDARunner ```: Non-Deterministic Pushdown Automaton
* ``` DTMRunner  ```: Deterministic Turing Machine


----------
Examples
----------
For further understanding take a look at ```examples``` tree.



### A simple Deterministic Finite Automaton ###
1) Define your states.

```java
 FState q0 = new FState("q0");
 FState q1 = new FState("q1");
```

2) Define your transitions.

```java
 q0.trans().when("1").goTo(q0);
 q0.trans().when("0").goTo(q1);
 q1.trans().when("1").when("0").goTo(q1);
```

3) Create your test words.
```java
 Word word = new Word("111110000");
```

4) Compute your string

```java
 DFARunner runner = new DFARunner(word, q0);
 runner.compute();
```

5) Do whatever you want with the result
```java
System.out.println(runner.getResult().getState());
```


----------
TODO
----------
* NFA to DFA conversion?
* DFA minimization?
* More examples, more use cases!