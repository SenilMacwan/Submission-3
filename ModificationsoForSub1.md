1. Requirements (Functional & Non-Functional)
Functional Requirements (FR)
The following new requirements need to be added:
•	FR-1: The system must allow storing multiple versions of a student’s submission.
•	FR-2: The system must keep track of each version with a unique identifier (e.g., version number or timestamp).
•	FR-3: The user must be able to select which submission version they want to run the test suite on.
•	FR-4: The system must maintain separate results for each version.
Non-Functional Requirements (NFR)
The following need to be added:
•	NFR-1: The system must maintain performance even when many versions are stored.
•	NFR-2: The system must store versions safely and never delete or overwrite earlier submissions.
2. Use Case Diagram
To support resubmissions, the use case diagram requires:
Modifications to Existing Use Cases
•	Load Submission
o	Must specify that loading a submission creates a new version, not replace the old one.
•	Execute Test Suite
o	Must include or extend Select Submission Version, since testing must now target a specific version.
4. Class Diagram
The class diagram must be updated more precisely:
New Class Needed
SubmissionVersion
Purpose: Represents a single version of a student's program submission.
Attributes:
•	versionNumber
•	timestamp
•	programFiles
•	results

