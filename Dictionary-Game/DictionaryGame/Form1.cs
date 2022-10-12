using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Data.OleDb;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;

namespace DictionaryGame
{
    public partial class Form1 : Form
    {
        public Form1()
        {
            InitializeComponent();
        }

        void control()
        {
            if(textBox1.Text != "")
            {
                if(textBox2.Text != "")
                {
                    button1.Enabled = true;
                }
            }
        }

        private void textBox1_TextChanged(object sender, EventArgs e)
        {
            control();
        }

        OleDbConnection connection = new OleDbConnection(@"YOUR DATABASE PATH");

        private void button1_Click(object sender, EventArgs e)
        {
            string query = "INSERT INTO person (Name, Surname) VALUES"+"(@name,@surname)";
            OleDbCommand cmd = new OleDbCommand(query, connection);
            cmd.Parameters.AddWithValue("@name", textBox1.Text);
            cmd.Parameters.AddWithValue("@surname", textBox2.Text);
            connection.Open();
            cmd.ExecuteNonQuery();
            connection.Close();
            this.Hide();
            Form2 form2 = new Form2();
            form2.Show();
        }

        private void textBox2_TextChanged(object sender, EventArgs e)
        {
            control();
        }
    }
}
