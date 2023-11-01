---
  layout: default.md
  title: "Developer Guide"
  pageNav: 3
---

# Tutorium Developer Guide

<!-- * Table of Contents -->
<page-nav-print />

--------------------------------------------------------------------------------------------------------------------

## **Acknowledgements**

_{ list here sources of all reused/adapted ideas, code, documentation, and third-party libraries -- include links to the original source as well }_

--------------------------------------------------------------------------------------------------------------------

## **Setting up, getting started**

Refer to the guide [_Setting up and getting started_](SettingUp.md).

--------------------------------------------------------------------------------------------------------------------

## **Design**

### Architecture

<puml src="diagrams/ArchitectureDiagram.puml" width="280" />

The ***Architecture Diagram*** given above explains the high-level design of the App.

Given below is a quick overview of main components and how they interact with each other.

**Main components of the architecture**

**`Main`** (consisting of classes [`Main`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/Main.java) and [`MainApp`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/MainApp.java)) is in charge of the app launch and shut down.
* At app launch, it initializes the other components in the correct sequence, and connects them up with each other.
* At shut down, it shuts down the other components and invokes cleanup methods where necessary.

The bulk of the app's work is done by the following four components:

* [**`UI`**](#ui-component): The UI of the App.
* [**`Logic`**](#logic-component): The command executor.
* [**`Model`**](#model-component): Holds the data of the App in memory.
* [**`Storage`**](#storage-component): Reads data from, and writes data to, the hard disk.

[**`Commons`**](#common-classes) represents a collection of classes used by multiple other components.

**How the architecture components interact with each other**

The *Sequence Diagram* below shows how the components interact with each other for the scenario where the user issues the command `delete 1`.

<puml src="diagrams/ArchitectureSequenceDiagram.puml" width="574" />

Each of the four main components (also shown in the diagram above),

* defines its *API* in an `interface` with the same name as the Component.
* implements its functionality using a concrete `{Component Name}Manager` class (which follows the corresponding API `interface` mentioned in the previous point.

For example, the `Logic` component defines its API in the `Logic.java` interface and implements its functionality using the `LogicManager.java` class which follows the `Logic` interface. Other components interact with a given component through its interface rather than the concrete class (reason: to prevent outside component's being coupled to the implementation of a component), as illustrated in the (partial) class diagram below.

<puml src="diagrams/ComponentManagers.puml" width="300" />

The sections below give more details of each component.

### UI component

The **API** of this component is specified in [`Ui.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/ui/Ui.java)

<puml src="diagrams/UiClassDiagram.puml" alt="Structure of the UI Component"/>

The UI consists of a `MainWindow` that is made up of parts e.g.`CommandBox`, `ResultDisplay`, `PersonListPanel`, `StatusBarFooter` etc. All these, including the `MainWindow`, inherit from the abstract `UiPart` class which captures the commonalities between classes that represent parts of the visible GUI.

The `UI` component uses the JavaFx UI framework. The layout of these UI parts are defined in matching `.fxml` files that are in the `src/main/resources/view` folder. For example, the layout of the [`MainWindow`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/ui/MainWindow.java) is specified in [`MainWindow.fxml`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/resources/view/MainWindow.fxml)

The `UI` component,

* executes user commands using the `Logic` component.
* listens for changes to `Model` data so that the UI can be updated with the modified data.
* keeps a reference to the `Logic` component, because the `UI` relies on the `Logic` to execute commands.
* depends on some classes in the `Model` component, as it displays `Student` object residing in the `Model`.

### Logic component

**API** : [`Logic.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/logic/Logic.java)

Here's a (partial) class diagram of the `Logic` component:

<puml src="diagrams/LogicClassDiagram.puml" width="550"/>

The sequence diagram below illustrates the interactions within the `Logic` component, taking `execute("delete 1")` API call as an example.

<puml src="diagrams/DeleteSequenceDiagram.puml" alt="Interactions Inside the Logic Component for the `delete 1` Command" />

<box type="info" seamless>

**Note:** The lifeline for `DeleteCommandParser` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.
</box>

How the `Logic` component works:

1. When `Logic` is called upon to execute a command, it is passed to an `AddressBookParser` object which in turn creates a parser that matches the command (e.g., `DeleteCommandParser`) and uses it to parse the command.
1. This results in a `Command` object (more precisely, an object of one of its subclasses e.g., `DeleteCommand`) which is executed by the `LogicManager`.
1. The command can communicate with the `Model` when it is executed (e.g. to delete a student).
1. The result of the command execution is encapsulated as a `CommandResult` object which is returned back from `Logic`.

Here are the other classes in `Logic` (omitted from the class diagram above) that are used for parsing a user command:

<puml src="diagrams/ParserClasses.puml" width="600"/>

How the parsing works:
* When called upon to parse a user command, the `AddressBookParser` class creates an `XYZCommandParser` (`XYZ` is a placeholder for the specific command name e.g., `AddCommandParser`) which uses the other classes shown above to parse the user command and create a `XYZCommand` object (e.g., `AddCommand`) which the `AddressBookParser` returns back as a `Command` object.
* All `XYZCommandParser` classes (e.g., `AddCommandParser`, `DeleteCommandParser`, ...) inherit from the `Parser` interface so that they can be treated similarly where possible e.g, during testing.

### Model component
**API** : [`Model.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/model/Model.java)

<puml src="diagrams/ModelClassDiagram.puml" width="450" />


The `Model` component,

* stores the address book data i.e., all `Student` objects (which are contained in a `UniquePersonList` object).
* stores the currently 'selected' `Student` objects (e.g., results of a search query) as a separate _filtered_ list which is exposed to outsiders as an unmodifiable `ObservableList<Student>` that can be 'observed' e.g. the UI can be bound to this list so that the UI automatically updates when the data in the list change.
* stores a `UserPref` object that represents the user’s preferences. This is exposed to the outside as a `ReadOnlyUserPref` objects.
* does not depend on any of the other three components (as the `Model` represents data entities of the domain, they should make sense on their own without depending on other components)

<box type="info" seamless>

[//]: # (**Note:** An alternative &#40;arguably, a more OOP&#41; model is given below. It has a `Subject` list in the `AddressBook`, which `Student` references. This allows `AddressBook` to only require one `Subject` object per unique subject, instead of each `Student` needing their own `Subject` objects.<br>)

<puml src="diagrams/BetterModelClassDiagram.puml" width="450" />

</box>


### Storage component

**API** : [`Storage.java`](https://github.com/AY2324S1-CS2103T-W13-2/tp/blob/master/src/main/java/seedu/address/storage/Storage.java)

<puml src="diagrams/StorageClassDiagram.puml" width="550" />

The `Storage` component,
* can save both address book data and user preference data in JSON format, and read them back into corresponding objects.
* inherits from both `AddressBookStorage` and `UserPrefStorage`, which means it can be treated as either one (if only the functionality of only one is needed).
* depends on some classes in the `Model` component (because the `Storage` component's job is to save/retrieve objects that belong to the `Model`)

### Common classes

Classes used by multiple components are in the `seedu.addressbook.commons` package.

--------------------------------------------------------------------------------------------------------------------

## **Implementation**

This section describes some noteworthy details on how certain features are implemented.

### Filter feature

#### Implementation

The `filter` command allows the user to display a list of students who fulfil all predicates specified within the command.

When the user enters a filter command, the `AddressBookParser` parses the user's input and returns a `FilterCommand`.

Each predicate entered by the user can be modelled by exactly one of the following classes: `StudentHasAddressPredicate`, `StudentHasEmailPredicate`, `StudentHasPhonePredicate`, `StudentIsGenderPredicate`, `StudentIsSecLevelPredicate`, `StudentNearestMrtIsPredicate` and `StudentTakesSubjectPredicate`. These predicates can be added to the `predicateList` field of type `StudentPredicateList` within each `FilterCommand` object.

The following sequence diagram shows how the `filter` command works. In this example, the user is executing the following command: `filter s/Physics g/M`.

<puml src="diagrams/FilterSequenceDiagram.puml" alt="FilterSequenceDiagram" />

When the `FilterCommandParser` parses the arguments to the `FilterCommand`, it creates a `StudentPredicateList`, to which the relevant predicates specified within the command are added. For example, using the example command given above, the `StudentPredicateList` would consist of 2 predicates: a `StudentTakesSubjectPredicate` and a `StudentIsGenderPredicate`.
These predicates are then combined into a single `Predicate<Student>`, using the `and()` method from the `Predicate` interface.

The following activity diagram summarizes what happens when a user executes a `filter` command:

<puml src="diagrams/FilterActivityDiagram.puml" alt="FilterActivityDiagram" width="250" />

#### Design considerations:

**Aspect: How the predicates specified within a single `FilterCommand` should be combined:**

* **Alternative 1 (current choice):** Combine predicates using the `and()` method from the `Predicate` interface.
  * Pros:
    * More in line with what the user would expect from a `filter` command.
  * Cons:
    * Users would experience less flexibility when using the command (for instance, the command `filter s/Physics s/Chemistry` cannot be used to display students taking Physics and/or Chemistry at the tuition centre).
* Alternative 2: Combine predicates using the `or()` method from the `Predicate` interface.
  * Pros:
    * Greater flexibility for users when filtering the list of students.
  * Cons:
    * Less in line with users' expectations of a `filter` command; not as intuitive.
* We made the choice of Alternative 1 over Alternative 2 as we found that more intuitive commands would be easier for users to learn and eventually master.

### Sort feature

#### Implementation

The `sort` command allows the user to sort the list of students in alphabetical order to enhance efficiency in searching.

When the user enters a sort command, the `AddressBookParser` parses the user's input and returns a `SortCommand`.

The predicate entered by the user can be modelled by the following class: `SortIn`.

The following sequence diagram shows how the `sort` command works. In this example, the user is executing the following command: `sort in/ASC`.

<puml src="diagrams/SortSequenceDiagram.puml" alt="FilterSequenceDiagram" />

When the `SortCommandParser` parses the argument to the `SortCommand`, the argument is stored as an attribute of type SortIn in `SortCommand`.
This predicate is then passed into the current model, using the `updateSortedPersonList()` method.

The following activity diagram summarizes what happens when a user executes a `sort` command:

<puml src="diagrams/FilterActivityDiagram.puml" alt="SortActivityDiagram" width="250" />

#### Design considerations:

**Aspect: How the student list is sorted internally:**

* **Alternative 1 (current choice):** Sort the student list in class `UniquePersonList` using method `sort`.
    * Pros:
        * Student list is sorted permanently, ensuring no repeated sorting needed in the next launch provided no new student is added or student's name is changed.
        * Enhance efficiency of looking through the student list, ensure no repeated sorting needed when doing consecutive commands such as `filter`.
    * Cons:
        * Users would be unable to view the unsorted student list again.
* Alternative 2: Sort the student list in class `ModelManager` using method `updateSortedPersonList`.
    * Pros:
        * Enable users to view the unsorted student list for every launch.
    * Cons:
        * Users have to resort the student list for every launch. 
* We made the choice of Alternative 1 over Alternative 2 as we insist on providing greater convenience.

### Import feature

#### Implementation

The `import` command allows the user to import .csv files containing their students' data in one go so that they do not need to add them one-by-one.

When the user enters a import command, the `AddressBookParser` parses the user's input using `ImportCommandParser` and returns a `ImportCommand`.

The following sequence diagram shows how the `import` command works. In this example, the user is executing the following command: `import student_data.csv`.

<puml src="diagrams/ImportSequenceDiagram.puml" alt="ImportSequenceDiagram" />

When the `ImportCommandParser` parses the arguments, it creates a list of `Student` objects using the data in the .csv file and passes the argument and the list into the `ImportCommand`. `ImportCommand` will then add the `Student` into the `AddressBook`.

The following activity diagram summarizes what happens when a user executes a `import` command:

<puml src="diagrams/ImportActivityDiagram.puml" alt="ImportActivityDiagram" width="250" />

#### Design considerations:

**Aspect: How to separate the attributes correctly from the imported data**

* **Alternative 1 (current choice):** Fixed column sequence for data in the imported .csv files.
    * Pros:
        * Students' data with comma such as address can be detected more easily and will not be split wrongly.
    * Cons:
        * Users would experience less flexibility when using the command (for instance, users need to ensure their column in their .csv files matches the sequence).
* Alternative 2: Flexible column sequence for data in the imported .csv files.
    * Pros:
        * Greater flexibility for users when importing students'data.
    * Cons:
        * Higher chance in wrong a splitting of students' data.
* We made the choice of Alternative 1 over Alternative 2 as we found that a fixed format would be easier for users to remember and use in the .csv files.


### Table feature

#### Implementation
The `table` command allows users to generate a statistical table either categorised by `gender`, `subject` or `sec-level`

When the user enters a table command, the `AddressBookParser` parses the user's input and return a `TableCommand`.

Note that there is no specifically a TableCommandParser for `TableCommand` just like `ListCommand`, `ExitCommand` and `HelpCommand`. The `AddressBookParser` can parse and return a `TableCommand`directly.

The parameters entered by user expected for a table command are either `g/`, `s/` and `l/`. When the `TableCommand` instance created by `AddressBookParser` executes, it will return the corresponding CommamdResult. E.g. `GenderTableCommandResult` created for the case `table g/` is entered by user. This `XXXTableCommandResult` carries the counts for each category that will be used for generating the table. 

The following sequence diagram shows how the `table` command works. In this example, the user is executing the following command: `table s/`

<puml src="diagrams/TableSequenceDiagram.puml" alt="TableSequenceDiagram" />

As shown in the sequence diagram, when the `AddressBookParser` parses the arguments to the TableCommand, it creates a TableCommand instance by passing in `s/` as argument so that when this `TableCommand` execute, it will return a `SubjectTableCommandResult` instance as specified by `s/`.

The following activity diagram summarizes what happens when a user executes a `table` command:

<puml src="diagrams/TableActivityDiagram.puml" alt="TableActivityDiagram" width="250" />

#### Design considerations:

**Aspect: How to parse the argument for table internally:**

* **Alternative 1 (current choice):** parse inside `TableCommand` and return the corresponding `XXXTableCommandResult`.
    * Pros: Easy to implement, more straightforward when the number of possible arguments is less.
    * Cons: May not be suitable when we want to create a complex statistical table, e.g. a two dimensional table.

* **Alternative 2:** Create a CommandParser specifically for TableCommand.
    * Pros: Provides a good abstraction when we are dealing with two dimensional table.
    * Cons: May be reduntant when we only want to create one dimensional table and the number of possible category is less.

* We made the choice of Alternative 1 over Alternative 2 as we found that the table we intend to create so far is one dimensional table and there are only three possible categories, that are , `g/` for gender, `s/` for subject and `l/` for sec-level.
_{more aspects and alternatives to be added}_


### \[Proposed\] Undo/redo feature

#### Proposed Implementation

The proposed undo/redo mechanism is facilitated by `VersionedAddressBook`. It extends `AddressBook` with an undo/redo history, stored internally as an `addressBookStateList` and `currentStatePointer`. Additionally, it implements the following operations:

* `VersionedAddressBook#commit()` — Saves the current address book state in its history.
* `VersionedAddressBook#undo()` — Restores the previous address book state from its history.
* `VersionedAddressBook#redo()` — Restores a previously undone address book state from its history.

These operations are exposed in the `Model` interface as `Model#commitAddressBook()`, `Model#undoAddressBook()` and `Model#redoAddressBook()` respectively.

Given below is an example usage scenario and how the undo/redo mechanism behaves at each step.

Step 1. The user launches the application for the first time. The `VersionedAddressBook` will be initialized with the initial address book state, and the `currentStatePointer` pointing to that single address book state.

<puml src="diagrams/UndoRedoState0.puml" alt="UndoRedoState0" />

Step 2. The user executes `delete 5` command to delete the 5th student in the address book. The `delete` command calls `Model#commitAddressBook()`, causing the modified state of the address book after the `delete 5` command executes to be saved in the `addressBookStateList`, and the `currentStatePointer` is shifted to the newly inserted address book state.

<puml src="diagrams/UndoRedoState1.puml" alt="UndoRedoState1" />

Step 3. The user executes `add n/David …​` to add a new student. The `add` command also calls `Model#commitAddressBook()`, causing another modified address book state to be saved into the `addressBookStateList`.

<puml src="diagrams/UndoRedoState2.puml" alt="UndoRedoState2" />

<box type="info" seamless>

**Note:** If a command fails its execution, it will not call `Model#commitAddressBook()`, so the address book state will not be saved into the `addressBookStateList`.

</box>

Step 4. The user now decides that adding the student was a mistake, and decides to undo that action by executing the `undo` command. The `undo` command will call `Model#undoAddressBook()`, which will shift the `currentStatePointer` once to the left, pointing it to the previous address book state, and restores the address book to that state.

<puml src="diagrams/UndoRedoState3.puml" alt="UndoRedoState3" />


<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index 0, pointing to the initial AddressBook state, then there are no previous AddressBook states to restore. The `undo` command uses `Model#canUndoAddressBook()` to check if this is the case. If so, it will return an error to the user rather
than attempting to perform the undo.

</box>

The following sequence diagram shows how the undo operation works:

<puml src="diagrams/UndoSequenceDiagram.puml" alt="UndoSequenceDiagram" />

<box type="info" seamless>

**Note:** The lifeline for `UndoCommand` should end at the destroy marker (X) but due to a limitation of PlantUML, the lifeline reaches the end of diagram.

</box>

The `redo` command does the opposite — it calls `Model#redoAddressBook()`, which shifts the `currentStatePointer` once to the right, pointing to the previously undone state, and restores the address book to that state.

<box type="info" seamless>

**Note:** If the `currentStatePointer` is at index `addressBookStateList.size() - 1`, pointing to the latest address book state, then there are no undone AddressBook states to restore. The `redo` command uses `Model#canRedoAddressBook()` to check if this is the case. If so, it will return an error to the user rather than attempting to perform the redo.

</box>

Step 5. The user then decides to execute the command `list`. Commands that do not modify the address book, such as `list`, will usually not call `Model#commitAddressBook()`, `Model#undoAddressBook()` or `Model#redoAddressBook()`. Thus, the `addressBookStateList` remains unchanged.

<puml src="diagrams/UndoRedoState4.puml" alt="UndoRedoState4" />

Step 6. The user executes `clear`, which calls `Model#commitAddressBook()`. Since the `currentStatePointer` is not pointing at the end of the `addressBookStateList`, all address book states after the `currentStatePointer` will be purged. Reason: It no longer makes sense to redo the `add n/David …​` command. This is the behavior that most modern desktop applications follow.

<puml src="diagrams/UndoRedoState5.puml" alt="UndoRedoState5" />

The following activity diagram summarizes what happens when a user executes a new command:

<puml src="diagrams/CommitActivityDiagram.puml" width="250" />

#### Design considerations:

**Aspect: How undo & redo executes:**

* **Alternative 1 (current choice):** Saves the entire address book.
  * Pros: Easy to implement.
  * Cons: May have performance issues in terms of memory usage.

* **Alternative 2:** Individual command knows how to undo/redo by
  itself.
  * Pros: Will use less memory (e.g. for `delete`, just save the student being deleted).
  * Cons: We must ensure that the implementation of each individual command are correct.

_{more aspects and alternatives to be added}_

### \[Proposed\] Data archiving

_{Explain here how the data archiving feature will be implemented}_


--------------------------------------------------------------------------------------------------------------------

## **Documentation, logging, testing, configuration, dev-ops**

* [Documentation guide](Documentation.md)
* [Testing guide](Testing.md)
* [Logging guide](Logging.md)
* [Configuration guide](Configuration.md)
* [DevOps guide](DevOps.md)

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Requirements**

### Product scope

**Target user profile**:

* works as a tuition administrative staff
* can type fast
* prefers typing to mouse interactions
* prefers using software to manage students' contacts
* wants to know his students well as part of his work
* wants to make use of statistics and technology to make marketing decisions

**Value proposition**: Our product will take in data as inputs and return statistical analysis.
Instead of showing information of independent individuals, our product aims to provide quantitative data analysis of students for users to draw conclusions on commonalities among students and their demographics, offering insights on marketing strategies.



### User stories

Priorities: High (must have) - `* * *`, Medium (nice to have) - `* *`, Low (unlikely to have) - `*`

| Priority | As a …​                                    | I want to …​                 | So that I can…​                                                        |
|----------|--------------------------------------------|------------------------------|------------------------------------------------------------------------|
| `* * *`  | As an admin staff           | I can insert new individual data into AB3 | so that I can gain new insights into the market by expanding student list|
| `* * *`  | As an admin staff           | I can delete data from AB3,  |  so that I can gain new insights into the market by removing irrelevant data|
| `* * *`  | As an admin staff                          | I can edit student information        | so that the data is correctly updated     |
| `* * *`  | As an admin staff                         | I can view student information     | so that I can know the details of students|
| `* *`    | As an admin staff                         | I can import data from various sources | so that it is more convenient to add data to the app |
| `* *`    | As an admin staff                        | I can save all student data into storage / database | so that I can access it again in the future |
| `* *`    | As an admin staff                          | I can archieve data | so that my data will be more organized  |
| `* *`    | As an admin staff                          | I can select multiple data from student list | so that I can perform an action on these data |
| `* *`    | As an admin staff                          | I can filter student data by date modified | so that i can distinguish outdated data |
| `* *`    | As an admin staff                          | I can filter student data by demographics | so that I can easily search for student by demographics  |
| `* *`    | As an admin staff                         | I can filter student data by location   | so that I can easily search for student by location   |
| `* *`    | As an admin staff                      | I can sort student data by ascending / descending   | so that I can easily search for student   |
| `* *`    | As an admin staff    | I can view the total number of students in the same age group   | so that I can tailor my marketing strategies by age group |
| `* *`    | As an admin staff           | I can view the total number of students with same gender   | so that I can tailor my marketing strategies by gender |
| `* *`    | As an admin staff      | I can view the total number of students in the same location   | so that I can tailor my marketing strategies by location.   |
| `* *`    | As an admin staff       | I can view the total number of students who took the same subject   | so that I can tailor my marketing strategies by subject taken   |
| `* *`    | As an admin staff                 | I can see the total number of students    | so that I can know how many people are in our tuition center   |
| `* *`    | As a new user of this application            | I can read the user guide   | so that I can familiarize myself with  this application   |
| `* *`    | As a new user of this application               | I can easily download and use this application   | so that I do not have to spend too much time and effort in figuring out setup settings   |
| `* *`    | As a new user            | I have access to help commadn / help page   | so that I can get help for the problem faced   |
| `* *`    | As an admin staff             | I can visualize data in different charts   | so that I can easily view the overall trend of the market   |
| `* *`    | As an admin staff                         | I can get the correlation between two factors that are related to my service   | so that I can have a quantitative analysis on the relationship between two factors related to my service  |
| `* *`    | As an admin staff                         | I can see the five number summary (min, Q1, mean, Q3, max) of my user data   | so that I can compare different groups of students' data   |
| `* *`    | As an admin staff             | I can export data as charts'graphical representations    | so that I can present my insights to my colleagues   |
| `* *`    | As an admin staff                        | I can view the trend of student attributes   | so that I can make predictions to target potential students   |
| `* *`    | As an admin staff               | I can save historical analyzed statistics   | so that I can learn form my past success and failure to imporve future strategies   |
| `* `    | As an admin staff               | I can collect data on online engagement   | so that I can conduct further analysis on digital marketing strategies   |
| `* `    | As an experienced user                | I can use shortcuts to perform tasks in the app   | so that I save time which I can spend on other activities   |
| `* `    | As an admin staff                | I can collect feedback(s) from my students.   | so that I can improve my service further based on those feedback(s)   |
| `* `    | As an admin staff                         | I can schedule my marketing campaigns and events   | so that I know when is my marketing events   |

*{More to be added}*

### Use cases

(For all use cases below, the **System** is the `Tutorium` and the **Actor** is the `Tuition Administrative Staff`, unless specified otherwise)

**Use case: UC01 - Add student data**

**MSS**

1. Staff chooses to add student data.
2. Tutorium stores the new student's data. <br>
    Use case ends.

**Extensions**

* 1a. Tutorium detects a format error or missing attributes in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

**Use case: UC02 - Delete student data**

**MSS**

1. Staff chooses to delete student data.
2. Tutorium deleted the student's data. <br>
    Use case ends.

**Extensions**

* 1a. Tutorium detects a format error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium detects multiple student data that match with the entered student’s name.
  * 1b1. Tutorium requests for the student’s email address.
  * 1b2. User enters the email address. <br>
    Steps 1b1-1b2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

* 1c. Tutorium could not find any student data that matches with the entered student’s name.
  * 1c1. Tutorium requests for a valid student’s name.
  * 1c2. User enters a new student’s name. <br>
    Steps 1c1-1c2 are repeated until the data entered are correct.  <br>
    Use case resumes from step 2.

**Use case: UC03 - Edit student data**

**MSS**

1. Staff chooses to edit student data.
2. Tutorium acknowledges the edit process.
3. Staff enters the new data.
4. Tutorium edits the student’s data. <br>
    Use case ends.

**Extensions**

* 1a. Tutorium detects a format error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command.  <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium detects multiple student data that match with the entered student’s name.
  * 1b1. Tutorium requests for the student’s email address.
  * 1b2. User enters the email address.  <br>
    Steps 1b1-1b2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

* 1c. Tutorium could not find any student data that matches with the entered student’s name.
  * 1c1. Tutorium requests for a valid student’s name.
  * 1c2. User enters a new student’s name.  <br>
    Steps 1c1-1c2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

* 3a. Tutorium detects a format error in the entered command.
  * 3a1. Tutorium requests for the correctly formatted command.
  * 3a2. User enters a new command.  <br>
    Steps 3a1-3a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 4.

* *a. At any time, User chooses to cancel the edit.
  * *a1. Tutorium requests to confirm the cancellation.
  * *a2. User chooses to cancel the edit.  <br>
    Use case ends.

**User case: UC04 - Search for student data**

**MSS**

1. Staff chooses to search student data with particular keywords.
2. Tutorium shows the list of student data that contain the keywords. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium could not find any student data that contains the keywords.
  * 1a1. Tutorium returns a message indicating no data found.  <br>
    Use case ends.

**User case: UC05 - Group student data**

**MSS**

1. Staff chooses to group student data by a tag.
2. Tutorium shows the list of student data that contain the tag. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a format error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 2a. Tutorium could not find any student data that contains the tag.
  * 2a1. Tutorium returns a message indicating no data found. <br>
    Use case ends

**User case: UC06 - View number of students who took the same subject**

**MSS**

1. Staff chooses to <ins> group student data by a course subject (UC05) </ins>.
2. Staff selects all students in the list.
3. Tutorium shows the number of students who took the subject. <br>
   Use case ends.

**Extensions**

* 2a. Tutorium detects a format error in the entered command.
  * 2a1. Tutorium requests for the correctly formatted command.
  * 2a2. User enters a new command. <br>
    Steps 2a1-2a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 3.

**User case: UC07 - Generate statistical table**

**MSS**

1. Staff chooses to generate a table by either gender, sec level, or subject categoy
2. Tutorium calculates the counts for each category and display it in a new table window. <br>

**Extensions**

* 1a. Tutorium detects a format error in the entered command.
    * 1a1. Tutorium requests for the correctly formatted command.
    * 1a2. User enters a new command. <br>
      Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
      Use case resumes from step 2.

* 1b. Tutorium could not find any category that matches with the entered category.
    * 1b1. Tutorium requests for a valid category.
    * 1b2. User enters a new category. <br>
      Steps 1b1-1b2 are repeated until the data entered are correct. <br>
      Use case resumes from step 2.

**User case: UC08 - Visualize data in charts**

**MSS**

1. Staff chooses to visualize data by a particular category.
2. Tutorium acknowledges the visualization.
3. Staff chooses a chart type as visual representation.
4. Tutorium shows the chart of the category chosen. <br>
   Use case ends.

**Extensions**

* 1a. Tutorium detects a format error in the entered command.
  * 1a1. Tutorium requests for the correctly formatted command.
  * 1a2. User enters a new command. <br>
    Steps 1a1-1a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 2.

* 1b. Tutorium could not find any category that matches with the entered category.
  * 1b1. Tutorium requests for a valid category.
  * 1b2. User enters a new category. <br>
    Steps 1b1-1b2 are repeated until the data entered are correct. <br>
    Use case resumes from step 2.

* 3a. Tutorium detects a format error in the entered command.
  * 3a1. Tutorium requests for the correctly formatted command.
  * 3a2. User enters a new command. <br>
    Steps 3a1-3a2 are repeated until the command entered is correctly formatted. <br>
    Use case resumes from step 4.

**User case: UC09 - Export charts**

**MSS**

1. Staff chooses to <ins> visualize data in charts (UC07) <ins>.
2. Staff chooses to export the charts to a file with a particular file path.
3. Tutorium exports the charts to the file. <br>
   Use case ends.

**Extensions**

* 2a. Tutorium detects a format error in the entered command.
  * 2a1. Tutorium requests for the correctly formatted command.
  * 2a2. User enters a new command. <br>
  Steps 2a1-2a2 are repeated until the command entered is correctly formatted. <br>
  Use case resumes from step 3.

* 2b. Tutorium could not find the file path entered.
  * 2b1. Tutorium requests for a valid file path.
  * 2b2. User enters a new file path. <br>
    Steps 2b1-2b2 are repeated until the file path entered is correct. <br>
    Use case resumes from step 3.

* *a. At any time, User chooses to cancel the export.
  * *a1. Tutorium requests to confirm the cancellation.
  * *a2. User chooses to cancel the export. <br>
    Use case ends.


### Non-Functional Requirements

1.  Should work on any _mainstream OS_ as long as it has Java `11` or above installed.
2.  Should be able to hold up to 1000 students without a noticeable sluggishness in performance for typical usage.
3.  A user with above-average typing speed for regular English text (i.e. not code, not system admin commands) should be able to accomplish most of the tasks faster using commands than using the mouse.
4.  The students' data format should be persistent.
5.  The application should be usable by a novice who has never interacted with command line interface before.
6.  The project is expected to adhere to a schedule that delivers several features implemented by 4 to 5 team members every week.

*{More to be added}*

### Glossary

* **Mainstream OS**: Windows, Linux, Unix, OS-X
* **Private contact detail**: A contact detail that is not meant to be shared with others
* **Student data**: Name, phone number, email, address, gender, sec level, nearest MRT and subject(s) for each student

--------------------------------------------------------------------------------------------------------------------

## **Appendix: Instructions for Manual Testing**

Given below are instructions to test the app manually.

<box type="info" seamless>

**Note:** These instructions only provide a starting point for testers to work on;
testers are expected to do more *exploratory* testing.

</box>

### Launch and shutdown

1. Initial launch

   1. Download the jar file and copy into an empty folder

   1. Double-click the jar file Expected: Shows the GUI with a set of sample contacts. The window size may not be optimum.

1. Saving window preferences

   1. Resize the window to an optimum size. Move the window to a different location. Close the window.

   1. Re-launch the app by double-clicking the jar file.<br>
       Expected: The most recent window size and location is retained.

1. _{ more test cases …​ }_

### Deleting a student

1. Deleting a student while all students are being shown

   1. Prerequisites: List all students using the `list` command. Multiple students in the list.

   1. Test case: `delete 1`<br>
      Expected: First contact is deleted from the list. Details of the deleted contact shown in the status message. Timestamp in the status bar is updated.

   1. Test case: `delete 0`<br>
      Expected: No student is deleted. Error details shown in the status message. Status bar remains the same.

   1. Other incorrect delete commands to try: `delete`, `delete x`, `...` (where x is larger than the list size)<br>
      Expected: Similar to previous.

1. _{ more test cases …​ }_

### Saving data

1. Dealing with missing/corrupted data files

   1. _{explain how to simulate a missing/corrupted file, and the expected behavior}_

1. _{ more test cases …​ }_

## **Appendix: Effort**

## **Appendix: Planned Enhancements**
