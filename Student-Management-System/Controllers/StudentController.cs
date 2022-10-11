using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using StudentManagementSystem.Models.EntityFramework;

namespace StudentManagementSystem.Controllers
{
    public class StudentController : Controller
    {
        // GET: Student
        DbSchoolEntities db = new DbSchoolEntities();
        public ActionResult Index()
        {
            var student = db.STUDENTS.ToList();
            return View(student);
        }

        [HttpGet]
         public ActionResult AddNewStudent()
        {
            List<SelectListItem> values = (from i in db.CLUBS.ToList()
                                           select new SelectListItem
                                           {
                                               Text = i.CLUBNAME,
                                               Value = i.CLUBID.ToString()
                                           }).ToList();
            ViewBag.clb = values;
            return View();
        }

         [HttpPost]
         public ActionResult AddNewStudent(STUDENTS p)
        {
            var clb = db.CLUBS.Where(m => m.CLUBID == p.CLUBS.CLUBID).FirstOrDefault();
            p.CLUBS = clb;
            db.STUDENTS.Add(p);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Delete(int id)
        {
            var std = db.STUDENTS.Find(id);
            db.STUDENTS.Remove(std);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            var std = db.STUDENTS.Find(id);

            List<SelectListItem> values = (from i in db.CLUBS.ToList()
                                           select new SelectListItem
                                           {
                                               Text = i.CLUBNAME,
                                               Value = i.CLUBID.ToString()
                                           }).ToList();
            ViewBag.clb = values;
            return View("Update", std);
        }

        public ActionResult StudentUpdate(STUDENTS p)
        {
            var std = db.STUDENTS.Find(p.STDID);
            std.STDNAME = p.STDNAME;
            std.STDSURNAME = p.STDSURNAME;
            std.STDGENDER = p.STDGENDER;
            std.STDCLUB = p.STDCLUB;
            db.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}