using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using StudentManagementSystem.Models.EntityFramework;

namespace StudentManagementSystem.Controllers
{
    public class LectureController : Controller
    {
        // GET: Lecture
        DbSchoolEntities db = new DbSchoolEntities();
        public ActionResult Index()
        {
            var lectures = db.LECTURES.ToList();
            return View(lectures);
        }
        [HttpGet]
        public ActionResult AddNewLecture()
        {
            return View();
        }

        [HttpPost]
        public ActionResult AddNewLecture(LECTURES p)
        {
            db.LECTURES.Add(p);
            db.SaveChanges();
            return View();
        }

        public ActionResult Delete(int id)
        {
            var lct = db.LECTURES.Find(id);
            db.LECTURES.Remove(lct);
            db.SaveChanges();
            return RedirectToAction("Index");
        }

        public ActionResult Update(int id)
        {
            var lecture = db.LECTURES.Find(id);
            return View("Update",lecture);
        }
        public ActionResult LectureUpdate(LECTURES p)
        {
            var lecture = db.LECTURES.Find(p.LECTUREID);
            lecture.LECTURENAME = p.LECTURENAME;
            db.SaveChanges();
            return RedirectToAction("Index");
        }
    }
}