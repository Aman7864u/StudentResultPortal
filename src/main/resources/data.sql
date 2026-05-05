-- Sample Students
INSERT INTO students (name, roll_number, email, department, semester, gender, phone_number, date_of_birth)
VALUES
  ('Aarav Sharma',    'CS2024001', 'aarav@example.com',    'Computer Science', '5th', 'Male',   '9876543210', '2002-03-14'),
  ('Priya Mehta',     'CS2024002', 'priya@example.com',    'Computer Science', '5th', 'Female', '9876543211', '2002-07-22'),
  ('Rohit Verma',     'IT2024001', 'rohit@example.com',    'Information Tech', '3rd', 'Male',   '9876543212', '2003-01-05'),
  ('Sneha Patil',     'EC2024001', 'sneha@example.com',    'Electronics',      '3rd', 'Female', '9876543213', '2003-05-18'),
  ('Kiran Reddy',     'ME2024001', 'kiran@example.com',    'Mechanical',       '7th', 'Male',   '9876543214', '2001-09-30');

-- Sample Subjects
INSERT INTO subjects (name, code, credits, department, semester)
VALUES
  ('Data Structures',          'CS301', 4, 'Computer Science', '5th'),
  ('Operating Systems',        'CS302', 4, 'Computer Science', '5th'),
  ('Database Management',      'CS303', 3, 'Computer Science', '5th'),
  ('Computer Networks',        'CS304', 3, 'Computer Science', '5th'),
  ('Web Technologies',         'IT201', 3, 'Information Tech', '3rd'),
  ('Digital Electronics',      'EC201', 4, 'Electronics',      '3rd'),
  ('Thermodynamics',           'ME401', 4, 'Mechanical',       '7th');
